package com.teamconfused.planmyplate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamconfused.planmyplate.dto.AiRecipeRequestDto;
import com.teamconfused.planmyplate.entity.Ingredient;
import com.teamconfused.planmyplate.entity.Recipe;
import com.teamconfused.planmyplate.entity.RecipeIngredient;
import com.teamconfused.planmyplate.repository.IngredientRepository;
import com.teamconfused.planmyplate.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GeminiAiService {

  private final RecipeRepository recipeRepository;
  private final IngredientRepository ingredientRepository;
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

      // Save and return the recipe
      return recipeRepository.save(recipe);

    } catch (Exception e) {
      throw new RuntimeException("Failed to generate recipe with AI: " + e.getMessage(), e);
    }
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
    prompt.append("  \"name\": \"Recipe Name\",\n");
    prompt.append("  \"description\": \"Brief description of the dish\",\n");
    prompt.append("  \"calories\": 450,\n");
    prompt.append("  \"prepTime\": 15,\n");
    prompt.append("  \"cookTime\": 30,\n");
    prompt.append("  \"servings\": ").append(request.getServings()).append(",\n");
    prompt.append("  \"instructions\": \"Step-by-step cooking instructions\",\n");
    prompt.append("  \"ingredients\": [\n");
    prompt.append("    {\n");
    prompt.append("      \"name\": \"ingredient name\",\n");
    prompt.append("      \"quantity\": 200,\n");
    prompt.append("      \"unit\": \"grams\"\n");
    prompt.append("    }\n");
    prompt.append("  ]\n");
    prompt.append("}\n");
    prompt.append("\nIMPORTANT: Return ONLY the JSON object, no markdown formatting or additional text.");

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

      Recipe recipe = new Recipe();
      recipe.setName(rootNode.get("name").asText());
      recipe.setDescription(rootNode.get("description").asText());
      recipe.setCalories(rootNode.get("calories").asInt());
      recipe.setPrepTime(rootNode.get("prepTime").asInt());
      recipe.setCookTime(rootNode.get("cookTime").asInt());
      recipe.setServings(rootNode.get("servings").asInt());
      recipe.setInstructions(rootNode.get("instructions").asText());
      recipe.setImageUrl(null); // AI doesn't generate images

      // Parse ingredients
      List<RecipeIngredient> recipeIngredients = new ArrayList<>();
      JsonNode ingredientsNode = rootNode.get("ingredients");

      if (ingredientsNode != null && ingredientsNode.isArray()) {
        for (JsonNode ingredientNode : ingredientsNode) {
          String ingredientName = ingredientNode.get("name").asText();
          int quantity = ingredientNode.get("quantity").asInt();
          String unit = ingredientNode.get("unit").asText();

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

    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AI response: " + e.getMessage(), e);
    }
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
