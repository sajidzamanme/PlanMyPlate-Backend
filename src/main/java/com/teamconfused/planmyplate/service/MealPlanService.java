package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.MealPlan;
import com.teamconfused.planmyplate.entity.User;
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
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Meal plan not found"));
    }

    public MealPlan create(Integer userId, MealPlan mealPlan) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
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
            throw new RuntimeException("Meal plan not found");
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
            throw new RuntimeException("Meal plan not found");
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
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

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
                        .orElseThrow(() -> new RuntimeException("Recipe not found: " + recipeId));

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

            // Collect ingredients for grocery list (unique recipes only)
            java.util.Set<com.teamconfused.planmyplate.entity.Ingredient> ingredients = new java.util.HashSet<>();
            java.util.Set<Integer> uniqueRecipeIds = new java.util.HashSet<>(recipeIds);
            List<com.teamconfused.planmyplate.entity.Recipe> uniqueRecipes = recipeRepository
                    .findAllById(uniqueRecipeIds);

            for (com.teamconfused.planmyplate.entity.Recipe recipe : uniqueRecipes) {
                if (recipe.getRecipeIngredients() != null) {
                    for (com.teamconfused.planmyplate.entity.RecipeIngredient ri : recipe.getRecipeIngredients()) {
                        ingredients.add(ri.getIngredient());
                    }
                }
            }

            // Add ingredients to grocery list
            groceryListService.addIngredients(userId, ingredients);
        }

        return repository.save(mealPlan);
    }
}
