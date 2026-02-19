package com.teamconfused.planmyplate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamconfused.planmyplate.dto.AiRecipeRequestDto;
import com.teamconfused.planmyplate.entity.*;
import com.teamconfused.planmyplate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeminiAiService {

  private final RecipeRepository recipeRepository;
  private final IngredientRepository ingredientRepository;
  private final UserPreferencesRepository userPreferencesRepository;
  private final MealPlanRepository mealPlanRepository;
  private final UserRepository userRepository;
  private final GroceryListService groceryListService;
  private final MealPlanService mealPlanService;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${gemini.api.key}")
  private String geminiApiKey;

  private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

  @Transactional
  public Recipe generateRecipe(AiRecipeRequestDto request) {
    try {
      // Build the prompt
      String prompt = buildPrompt(request);

      // Call Gemini API via REST
      String aiResponse = callGeminiApi(prompt);

      // Extract JSON from response (remove markdown code blocks if present)
      String jsonResponse = extractJson(aiResponse);

      // Parse the JSON response
      Recipe recipe = parseRecipeFromJson(jsonResponse, request);

      // Save only if it's a new recipe
      if (recipe.getRecipeId() == null) {
        return recipeRepository.save(recipe);
      }
      return recipe;

    } catch (Exception e) {
      throw new RuntimeException("Failed to generate recipe with AI: " + e.getMessage(), e);
    }
  }

  @Transactional
  public MealPlan generateWeeklyMealPlan(Integer userId, LocalDate startDate) {
    try {
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new RuntimeException("User not found"));

      UserPreferences prefs = userPreferencesRepository.findByUser_UserId(userId)
          .orElse(null); // Optional, can be null

      // Deactivate existing meal plans and grocery lists
      mealPlanService.deactivateActiveMealPlans(userId);
      groceryListService.deactivateActiveLists(userId);

      MealPlan mealPlan = new MealPlan();
      mealPlan.setUser(user);
      mealPlan.setStartDate(startDate);
      mealPlan.setDuration(7);
      mealPlan.setStatus("active");

      List<MealSlot> slots = new ArrayList<>();
      List<String> mealTypes = Arrays.asList("Breakfast", "Lunch", "Dinner");

      // Generate recipes for each meal type (7 recipes each)
      for (String mealType : mealTypes) {
        String prompt = buildWeeklyMenuPrompt(prefs, mealType);
        String aiResponse = callGeminiApi(prompt);
        String jsonResponse = extractJson(aiResponse);
        List<Recipe> recipes = parseRecipesFromJson(jsonResponse);

        // Ensure we handle potentially fewer recipes than 7, though prompt asks for 7
        for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
          Recipe recipe;
          if (recipes.size() > dayOfWeek) {
            recipe = recipes.get(dayOfWeek);
          } else if (!recipes.isEmpty()) {
            // Fallback to cycling through generated recipes if fewer than 7 returned
            recipe = recipes.get(dayOfWeek % recipes.size());
          } else {
            continue; // Skip slot if no recipes generated
          }

          // Check if recipe exists or save new one
          // Basic check by name to avoid duplicates if re-generating same content?
          // For now, simpler to just save as new AI recipe or use existing logic if we
          // had unique constraints
          // The parsing logic below creates new ingredients but doesn't check existing
          // recipes by name deeply
          // For MVP, we will save the recipe.
          // If the AI says it's an existing recipe, we use it directly
          if (recipe.getRecipeId() != null) {
            // Optionally we could fetch it from DB to ensure it exists,
            // but parseRecipeNode already handles this.
          } else {
            recipe = recipeRepository.save(recipe);
          }

          MealSlot slot = new MealSlot();
          slot.setMealPlan(mealPlan);
          slot.setRecipe(recipe);
          slot.setMealType(mealType);
          slot.setDayNumber(dayOfWeek + 1); // 1-based day index
          slot.setSlotIndex(getSlotIndex(mealType, dayOfWeek)); // 0-20 index if needed, or by day/type

          slots.add(slot);
        }
      }

      mealPlan.setSlots(slots);

      // Collect unique ingredients for the grocery list with aggregated quantities
      // key = (ingredientId + "_" + unit.toLowerCase()), value = DTO
      Map<String, com.teamconfused.planmyplate.dto.IngredientQuantityDto> ingredientMap = new HashMap<>();

      for (MealSlot slot : slots) {
        Recipe recipe = slot.getRecipe();
        if (recipe != null && recipe.getRecipeIngredients() != null) {
          for (RecipeIngredient ri : recipe.getRecipeIngredients()) {
            Integer riQuantity = ri.getQuantity();
            if (riQuantity == null || riQuantity <= 0) {
              riQuantity = 1; // Default to 1
            }

            String unit = (ri.getUnit() != null && !ri.getUnit().trim().isEmpty()) ? ri.getUnit() : "unit";
            String key = ri.getIngredient().getIngId() + "_" + unit.toLowerCase();

            if (ingredientMap.containsKey(key)) {
              com.teamconfused.planmyplate.dto.IngredientQuantityDto existing = ingredientMap.get(key);
              existing.setQuantity(existing.getQuantity() + riQuantity);
            } else {
              com.teamconfused.planmyplate.dto.IngredientQuantityDto dto = new com.teamconfused.planmyplate.dto.IngredientQuantityDto(
                  ri.getIngredient(), riQuantity, unit);
              ingredientMap.put(key, dto);
            }
          }
        }
      }

      // Add ingredients to the user's active grocery list with aggregated quantities
      if (!ingredientMap.isEmpty()) {
        groceryListService.addIngredientsWithQuantities(userId, new ArrayList<>(ingredientMap.values()));
      }

      return mealPlanRepository.save(mealPlan);

    } catch (Exception e) {
      throw new RuntimeException("Failed to generate weekly meal plan: " + e.getMessage(), e);
    }
  }

  private int getSlotIndex(String mealType, int dayOfWeek) {
    // 3 meals per day.
    // Day 0: Breakfast=0, Lunch=1, Dinner=2
    // Day 1: Breakfast=3, Lunch=4, Dinner=5
    int typeOffset = 0;
    if ("Lunch".equalsIgnoreCase(mealType))
      typeOffset = 1;
    else if ("Dinner".equalsIgnoreCase(mealType))
      typeOffset = 2;

    return (dayOfWeek * 3) + typeOffset;
  }

  private String callGeminiApi(String prompt) {
    try {
      // Build request body
      Map<String, Object> requestBody = new HashMap<>();

      Map<String, Object> content = new HashMap<>();
      Map<String, String> part = new HashMap<>();
      part.put("text", prompt);
      content.put("parts", Collections.singletonList(part));
      requestBody.put("contents", Collections.singletonList(content));

      // Set headers
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      // Create request entity
      HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

      // Make API call
      String url = GEMINI_API_URL + "?key=" + geminiApiKey;
      ResponseEntity<String> response = restTemplate.exchange(
          url,
          HttpMethod.POST,
          entity,
          String.class);

      // Parse response
      String body = response.getBody();
      if (body == null || body.isEmpty()) {
        throw new RuntimeException("Empty response body from Gemini API");
      }

      JsonNode root = objectMapper.readTree(body);
      JsonNode candidates = root.get("candidates");
      if (candidates != null && candidates.isArray() && candidates.size() > 0) {
        JsonNode firstCandidate = candidates.get(0);
        JsonNode content2 = firstCandidate.get("content");
        if (content2 != null) {
          JsonNode parts = content2.get("parts");
          if (parts != null && parts.isArray() && parts.size() > 0) {
            return parts.get(0).get("text").asText();
          }
        }
      }

      throw new RuntimeException("Unexpected response structure from Gemini API: " + body);

    } catch (org.springframework.web.client.HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        String availableModels = listAvailableModels();
        throw new RuntimeException("Gemini Model not found. Available models for your key: " + availableModels, e);
      }
      throw new RuntimeException(
          "Gemini API call failed with status " + e.getStatusCode() + ": " + e.getResponseBodyAsString(), e);
    } catch (org.springframework.web.client.HttpServerErrorException e) {
      throw new RuntimeException(
          "Gemini API call failed with status " + e.getStatusCode() + ": " + e.getResponseBodyAsString(), e);
    } catch (Exception e) {
      throw new RuntimeException("Failed to call Gemini API: " + e.getMessage(), e);
    }
  }

  private String getDatabaseSummary() {
    List<Recipe> recipes = recipeRepository.findAll();
    StringBuilder summary = new StringBuilder();
    summary.append("Existing Recipes in Database:\n");
    for (Recipe r : recipes) {
      summary.append("- ID: ").append(r.getRecipeId()).append(", Name: ").append(r.getName()).append(", Ingredients: ");
      if (r.getRecipeIngredients() != null) {
        String ingredients = r.getRecipeIngredients().stream()
            .map(ri -> {
              String name = ri.getIngredient().getName();
              if (ri.getIngredient().getTags() != null && !ri.getIngredient().getTags().isEmpty()) {
                String tags = ri.getIngredient().getTags().stream()
                    .map(IngredientTag::getTagName)
                    .collect(Collectors.joining("|"));
                return name + "(" + tags + ")";
              }
              return name;
            })
            .collect(Collectors.joining(", "));
        summary.append(ingredients);
      }
      summary.append("\n");
    }
    return summary.toString();
  }

  private String listAvailableModels() {
    try {
      String url = "https://generativelanguage.googleapis.com/v1beta/models?key=" + geminiApiKey;
      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

      JsonNode root = objectMapper.readTree(response.getBody());
      JsonNode models = root.get("models");
      List<String> modelNames = new ArrayList<>();

      if (models != null && models.isArray()) {
        for (JsonNode model : models) {
          JsonNode nameNode = model.get("name");
          if (nameNode != null) {
            modelNames.add(nameNode.asText());
          }
        }
      }
      return String.join(", ", modelNames);
    } catch (Exception e) {
      return "Failed to list models: " + e.getMessage();
    }
  }

  private String buildPrompt(AiRecipeRequestDto request) {
    StringBuilder prompt = new StringBuilder();
    prompt.append(
        "You are a professional chef and recipe creator. Generate a detailed recipe based on the following requirements:\n\n");

    prompt.append(getDatabaseSummary()).append("\n");
    prompt.append(
        "IMPORTANT: Before creating a new recipe, check if one of the 'Existing Recipes' above matches the user's request. If a suitable recipe exists, reuse it.\n\n");

    if (request.getAvailableIngredients() != null && !request.getAvailableIngredients().isEmpty()) {
      prompt.append("Available Ingredients: ").append(String.join(", ", request.getAvailableIngredients()))
          .append("\n");
    }

    if (request.getMaxCalories() != null) {
      prompt.append("Maximum Calories: ").append(request.getMaxCalories()).append(" per serving\n");
    }

    if (request.getCuisineType() != null && !request.getCuisineType().isEmpty()) {
      prompt.append("Cuisine Type: ").append(request.getCuisineType()).append("\n");
    }

    if (request.getAllergies() != null && !request.getAllergies().isEmpty()) {
      prompt.append("Allergies to Avoid: ").append(String.join(", ", request.getAllergies())).append("\n");
    }

    if (request.getDietaryPreference() != null && !request.getDietaryPreference().isEmpty()) {
      prompt.append("Dietary Preference: ").append(request.getDietaryPreference()).append("\n");
    }

    if (request.getMood() != null && !request.getMood().isEmpty()) {
      prompt.append("Mood/Occasion: ").append(request.getMood()).append("\n");
    }

    prompt.append("Servings: ").append(request.getServings()).append("\n");

    if (request.getMaxCookingTime() != null) {
      prompt.append("Maximum Total Cooking Time: ").append(request.getMaxCookingTime()).append(" minutes\n");
    }

    prompt.append("\nPlease provide the recipe in the following JSON format ONLY (no additional text):\n");
    prompt.append("{\n");
    prompt.append("  \"isNew\": true, // Set to false if you are reusing an existing recipe\n");
    prompt.append("  \"recipeId\": null, // Provide the ID if you are reusing an existing recipe, otherwise null\n");
    prompt.append("  \"name\": \"Recipe Name\",\n");
    prompt.append("  \"description\": \"Brief description of the dish\",\n");
    prompt.append("  \"calories\": 450,\n");
    prompt.append("  \"prepTime\": 15,\n");
    prompt.append("  \"cookTime\": 30,\n");
    prompt.append("  \"servings\": ").append(request.getServings()).append(",\n");
    prompt.append("  \"instructions\": \"Step-by-step cooking instructions\",\n");
    prompt.append(
        "  \"imageUrl\": \"https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&q=80&w=1080\", // Provide a REAL, valid high-quality Unsplash image URL suitable for the recipe. Do NOT hallucinate IDs; use well-known food photo IDs from Unsplash.\n");
    prompt.append("  \"ingredients\": [\n");
    prompt.append("    {\n");
    prompt.append("      \"name\": \"ingredient name\",\n");
    prompt.append("      \"quantity\": 200,\n");
    prompt.append("      \"unit\": \"grams\"\n");
    prompt.append("    }\n");
    prompt.append("  ]\n");
    prompt.append("}\n");
    prompt.append(
        "\nIMPORTANT: Return ONLY the JSON object, no markdown formatting or additional text. If reusing a recipe, still provide its full details but set isNew to false and provide the recipeId.");

    return prompt.toString();
  }

  private String buildWeeklyMenuPrompt(UserPreferences prefs, String mealType) {
    StringBuilder prompt = new StringBuilder();
    prompt.append("You are a professional chef. Generate 7 DISTINCT ").append(mealType)
        .append(" recipes for a weekly meal plan.\n\n");

    prompt.append(getDatabaseSummary()).append("\n");
    prompt.append(
        "IMPORTANT: Prefer reusing 'Existing Recipes' from the list above if they fit the plan. Only create new recipes if no suitable ones exist.\n\n");

    if (prefs != null) {
      if (prefs.getDiet() != null) {
        prompt.append("Dietary Preference: ").append(prefs.getDiet().getDietName()).append("\n");
      }
      if (prefs.getAllergies() != null && !prefs.getAllergies().isEmpty()) {
        String allergies = prefs.getAllergies().stream().map(Allergy::getAllergyName).collect(Collectors.joining(", "));
        prompt.append("Allergies to Avoid: ").append(allergies).append("\n");
      }
      if (prefs.getDislikes() != null && !prefs.getDislikes().isEmpty()) {
        String dislikes = prefs.getDislikes().stream().map(Ingredient::getName).collect(Collectors.joining(", "));
        prompt.append("Ingredients to Avoid/Dislike: ").append(dislikes).append("\n");
      }
    }

    prompt.append("\nPlease provide the response in the following JSON format ONLY:\n");
    prompt.append("{\n");
    prompt.append("  \"recipes\": [\n");
    prompt.append("    {\n");
    prompt.append("      \"isNew\": true,\n");
    prompt.append("      \"recipeId\": null, // if isNew is false, provide the ID from the database\n");
    prompt.append("      \"name\": \"Recipe Name\",\n");
    prompt.append("      \"description\": \"Brief description\",\n");
    prompt.append("      \"calories\": 400,\n");
    prompt.append("      \"prepTime\": 10,\n");
    prompt.append("      \"cookTime\": 20,\n");
    prompt.append("      \"servings\": 2,\n");
    prompt.append("      \"instructions\": \"Step 1... Step 2...\",\n");
    prompt.append(
        "      \"imageUrl\": \"https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&q=80&w=1080\", // Provide a REAL Unsplash URL\n");
    prompt.append("      \"ingredients\": [\n");
    prompt.append("        {\"name\": \"Ing 1\", \"quantity\": 100, \"unit\": \"g\"}\n");
    prompt.append("      ]\n");
    prompt.append("    }\n");
    prompt.append("  ]\n");
    prompt.append("}\n");
    prompt
        .append("Ensure there are exactly 7 recipes. For existing ones, provide full details but set isNew to false.");
    return prompt.toString();
  }

  private String extractJson(String response) {
    if (response == null || response.isEmpty()) {
      return "";
    }

    // Try to find the first '{' and last '}' to extract the JSON object
    int start = response.indexOf('{');
    int end = response.lastIndexOf('}');

    if (start != -1 && end != -1 && end > start) {
      return response.substring(start, end + 1);
    }

    return response.trim();
  }

  private Recipe parseRecipeFromJson(String jsonResponse, AiRecipeRequestDto request) {
    try {
      JsonNode rootNode = objectMapper.readTree(jsonResponse);
      return parseRecipeNode(rootNode);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AI response: " + e.getMessage(), e);
    }
  }

  private List<Recipe> parseRecipesFromJson(String jsonResponse) {
    try {
      JsonNode rootNode = objectMapper.readTree(jsonResponse);
      JsonNode recipesNode = rootNode.get("recipes");
      List<Recipe> recipes = new ArrayList<>();
      if (recipesNode != null && recipesNode.isArray()) {
        for (JsonNode node : recipesNode) {
          recipes.add(parseRecipeNode(node));
        }
      }
      return recipes;
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse weekly recipes: " + e.getMessage(), e);
    }
  }

  private Recipe parseRecipeNode(JsonNode node) {
    boolean isNew = node.has("isNew") ? node.get("isNew").asBoolean() : true;
    Integer existingId = node.has("recipeId") && !node.get("recipeId").isNull() ? node.get("recipeId").asInt() : null;

    if (!isNew && existingId != null) {
      Optional<Recipe> existing = recipeRepository.findById(existingId);
      if (existing.isPresent()) {
        return existing.get();
      }
    }

    // Double check by name if isNew is true but same name exists (deduplication)
    String name = node.has("name") ? node.get("name").asText() : "Unknown Recipe";
    Optional<Recipe> byName = recipeRepository.findByName(name);
    if (byName.isPresent()) {
      return byName.get();
    }

    Recipe recipe = new Recipe();
    recipe.setName(name);
    recipe.setDescription(node.has("description") ? node.get("description").asText() : "");
    recipe.setCalories(node.has("calories") ? node.get("calories").asInt() : 0);
    recipe.setPrepTime(node.has("prepTime") ? node.get("prepTime").asInt() : 0);
    recipe.setCookTime(node.has("cookTime") ? node.get("cookTime").asInt() : 0);
    recipe.setServings(node.has("servings") ? node.get("servings").asInt() : 1);
    recipe.setInstructions(node.has("instructions") ? node.get("instructions").asText() : "");
    recipe.setImageUrl(node.has("imageUrl") ? node.get("imageUrl").asText() : null);

    // Parse ingredients
    List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    JsonNode ingredientsNode = node.get("ingredients");

    if (ingredientsNode != null && ingredientsNode.isArray()) {
      for (JsonNode ingredientNode : ingredientsNode) {
        String ingredientName = ingredientNode.has("name") ? ingredientNode.get("name").asText() : "Unknown";
        int quantity = ingredientNode.has("quantity") ? ingredientNode.get("quantity").asInt() : 0;
        String unit = ingredientNode.has("unit") ? ingredientNode.get("unit").asText() : "";

        // Find or create ingredient in database
        Ingredient ingredient = findOrCreateIngredient(ingredientName);

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(quantity);
        recipeIngredient.setUnit(unit);

        recipeIngredients.add(recipeIngredient);
      }
    }

    recipe.setRecipeIngredients(recipeIngredients);
    return recipe;
  }

  private Ingredient findOrCreateIngredient(String name) {
    Optional<Ingredient> existing = ingredientRepository.findByName(name);
    if (existing.isPresent()) {
      return existing.get();
    }

    // Create new ingredient with default price
    Ingredient newIngredient = new Ingredient();
    newIngredient.setName(name);
    newIngredient.setPrice(java.math.BigDecimal.ZERO); // Default price, can be updated later
    return ingredientRepository.save(newIngredient);
  }
}
