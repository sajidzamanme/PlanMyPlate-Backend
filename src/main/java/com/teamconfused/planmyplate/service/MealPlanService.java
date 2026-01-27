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
        if (mealPlan.getRecipes() != null)
            existing.setRecipes(mealPlan.getRecipes());
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

        if (dto.getRecipeIds() != null && !dto.getRecipeIds().isEmpty()) {
            List<com.teamconfused.planmyplate.entity.Recipe> recipes = recipeRepository.findAllById(dto.getRecipeIds());
            mealPlan.setRecipes(new java.util.HashSet<>(recipes));

            // Collect ingredients
            java.util.Set<com.teamconfused.planmyplate.entity.Ingredient> ingredients = new java.util.HashSet<>();
            for (com.teamconfused.planmyplate.entity.Recipe recipe : recipes) {
                if (recipe.getIngredients() != null) {
                    ingredients.addAll(recipe.getIngredients());
                }
            }

            // Add ingredients to grocery list
            groceryListService.addIngredients(userId, ingredients);
        }

        return repository.save(mealPlan);
    }
}
