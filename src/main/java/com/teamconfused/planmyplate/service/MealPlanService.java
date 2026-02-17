package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.MealPlan;
import com.teamconfused.planmyplate.entity.User;
import com.teamconfused.planmyplate.exception.ResourceNotFoundException;
import com.teamconfused.planmyplate.repository.MealPlanRepository;
import com.teamconfused.planmyplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlanService {
    private final MealPlanRepository repository;
    private final UserRepository userRepository;

    public List<MealPlan> getByUserId(Integer userId) {
        return repository.findByUser_UserId(userId);
    }

    public MealPlan getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal plan", id));
    }

    public MealPlan create(Integer userId, MealPlan mealPlan) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        mealPlan.setUser(user);
        if (mealPlan.getStartDate() == null) {
            mealPlan.setStartDate(LocalDate.now());
        }
        if (mealPlan.getStatus() == null) {
            mealPlan.setStatus("active");
        }
        return repository.save(mealPlan);
    }

    public MealPlan update(Integer id, MealPlan mealPlan) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Meal plan", id);
        MealPlan existing = repository.findById(id).get();
        if (mealPlan.getStatus() != null)
            existing.setStatus(mealPlan.getStatus());
        if (mealPlan.getDuration() != null)
            existing.setDuration(mealPlan.getDuration());
        // if (mealPlan.getRecipes() != null)
        // existing.setRecipes(mealPlan.getRecipes());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Meal plan", id);
        repository.deleteById(id);
    }

    public List<MealPlan> getByUserIdAndStatus(Integer userId, String status) {
        return repository.findByUser_UserIdAndStatus(userId, status);
    }

    public List<MealPlan> getWeeklyMealPlans(Integer userId) {
        return repository.findByUser_UserIdAndDuration(userId, 7);
    }

    private final com.teamconfused.planmyplate.repository.RecipeRepository recipeRepository;
    private final GroceryListService groceryListService;

    @org.springframework.transaction.annotation.Transactional
    public MealPlan createWithRecipes(Integer userId, com.teamconfused.planmyplate.dto.MealPlanRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));

        MealPlan mealPlan = new MealPlan();
        mealPlan.setUser(user);
        mealPlan.setStartDate(dto.getStartDate() != null ? dto.getStartDate() : LocalDate.now());
        mealPlan.setDuration(dto.getDuration() != null ? dto.getDuration() : 7); // Default 7 days
        mealPlan.setStatus("active");

        List<com.teamconfused.planmyplate.entity.MealSlot> slots = new java.util.ArrayList<>();
        mealPlan.setSlots(slots);

        if (dto.getRecipeIds() != null && !dto.getRecipeIds().isEmpty()) {
            List<Integer> recipeIds = dto.getRecipeIds();
            // We expect 21 recipes. If less, we might cycle or just stop. If more, we use
            // the first 21?
            // User requirement: "breakfast, lunch, dinner, breakfast..." sequence.

            // Loop for 21 slots (7 days * 3 meals)
            for (int i = 0; i < 21; i++) {
                if (i >= recipeIds.size())
                    break; // Prevent index out of bounds if less than 21 provided

                Integer recipeId = recipeIds.get(i);
                com.teamconfused.planmyplate.entity.Recipe recipe = recipeRepository.findById(recipeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Recipe", recipeId));

                com.teamconfused.planmyplate.entity.MealSlot slot = new com.teamconfused.planmyplate.entity.MealSlot();
                slot.setMealPlan(mealPlan);
                slot.setRecipe(recipe);
                slot.setSlotIndex(i);

                // Calculate day (1-7) and meal type
                int day = (i / 3) + 1;
                int typeIndex = i % 3;
                String type = typeIndex == 0 ? "Breakfast" : (typeIndex == 1 ? "Lunch" : "Dinner");

                slot.setDayNumber(day);
                slot.setMealType(type);

                slots.add(slot);
            }

            // Collect ingredients for grocery list with aggregated quantities
            // Use a map to aggregate quantities: key = (ingredientId, unit), value = total
            // quantity
            java.util.Map<String, com.teamconfused.planmyplate.dto.IngredientQuantityDto> ingredientMap = new java.util.HashMap<>();

            // Count how many times each recipe appears in the meal plan
            java.util.Map<Integer, Integer> recipeCount = new java.util.HashMap<>();
            for (Integer recipeId : recipeIds) {
                recipeCount.put(recipeId, recipeCount.getOrDefault(recipeId, 0) + 1);
            }

            // Get unique recipes
            java.util.Set<Integer> uniqueRecipeIds = new java.util.HashSet<>(recipeIds);
            List<com.teamconfused.planmyplate.entity.Recipe> uniqueRecipes = recipeRepository
                    .findAllById(uniqueRecipeIds);

            for (com.teamconfused.planmyplate.entity.Recipe recipe : uniqueRecipes) {
                if (recipe.getRecipeIngredients() != null) {
                    int multiplier = recipeCount.get(recipe.getRecipeId());

                    for (com.teamconfused.planmyplate.entity.RecipeIngredient ri : recipe.getRecipeIngredients()) {
                        Integer riQuantity = ri.getQuantity();
                        if (riQuantity == null) {
                            riQuantity = 0; // Or 1, depending on business logic, but 0 is safer for aggregation
                        }

                        String key = ri.getIngredient().getIngId() + "_" +
                                (ri.getUnit() != null ? ri.getUnit().toLowerCase() : "unit");

                        if (ingredientMap.containsKey(key)) {
                            // Aggregate quantity
                            com.teamconfused.planmyplate.dto.IngredientQuantityDto existing = ingredientMap.get(key);
                            existing.setQuantity(existing.getQuantity() + (riQuantity * multiplier));
                        } else {
                            // Add new entry
                            com.teamconfused.planmyplate.dto.IngredientQuantityDto ingredientDto = new com.teamconfused.planmyplate.dto.IngredientQuantityDto(
                                    ri.getIngredient(),
                                    riQuantity * multiplier,
                                    ri.getUnit() != null ? ri.getUnit() : "unit");
                            ingredientMap.put(key, ingredientDto);
                        }
                    }
                }
            }

            // Add ingredients to grocery list with quantities
            if (!ingredientMap.isEmpty()) {
                groceryListService.addIngredientsWithQuantities(userId,
                        new java.util.ArrayList<>(ingredientMap.values()));
            }
        }

        return repository.save(mealPlan);
    }
}
