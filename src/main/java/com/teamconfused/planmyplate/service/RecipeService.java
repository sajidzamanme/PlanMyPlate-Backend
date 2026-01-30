package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.dto.RecipeCreateDto;
import com.teamconfused.planmyplate.dto.RecipeIngredientDto;
import com.teamconfused.planmyplate.entity.Ingredient;
import com.teamconfused.planmyplate.entity.Recipe;
import com.teamconfused.planmyplate.entity.RecipeIngredient;
import com.teamconfused.planmyplate.repository.IngredientRepository;
import com.teamconfused.planmyplate.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository repository;
    private final IngredientRepository ingredientRepository;

    public List<Recipe> getAll() {
        return repository.findAll();
    }

    public Recipe getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public Recipe createRecipe(RecipeCreateDto dto) {
        Recipe recipe = new Recipe();
        recipe.setName(dto.getName());
        recipe.setDescription(dto.getDescription());
        recipe.setCalories(dto.getCalories());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServings(dto.getServings());
        recipe.setInstructions(dto.getInstructions());
        recipe.setImageUrl(dto.getImageUrl());

        // Create RecipeIngredient objects
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        if (dto.getIngredients() != null) {
            for (RecipeIngredientDto ingredientDto : dto.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findById(ingredientDto.getIngId())
                        .orElseThrow(() -> new RuntimeException("Ingredient not found: " + ingredientDto.getIngId()));

                RecipeIngredient ri = new RecipeIngredient();
                ri.setRecipe(recipe);
                ri.setIngredient(ingredient);
                ri.setQuantity(ingredientDto.getQuantity());
                ri.setUnit(ingredientDto.getUnit());
                recipeIngredients.add(ri);
            }
        }
        recipe.setRecipeIngredients(recipeIngredients);

        return repository.save(recipe);
    }

    public Recipe updateRecipe(Integer id, RecipeCreateDto dto) {
        Recipe existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (dto.getName() != null)
            existing.setName(dto.getName());
        if (dto.getDescription() != null)
            existing.setDescription(dto.getDescription());
        if (dto.getCalories() != null)
            existing.setCalories(dto.getCalories());
        if (dto.getPrepTime() != null)
            existing.setPrepTime(dto.getPrepTime());
        if (dto.getCookTime() != null)
            existing.setCookTime(dto.getCookTime());
        if (dto.getServings() != null)
            existing.setServings(dto.getServings());
        if (dto.getInstructions() != null)
            existing.setInstructions(dto.getInstructions());
        if (dto.getImageUrl() != null)
            existing.setImageUrl(dto.getImageUrl());

        // Update ingredients if provided
        if (dto.getIngredients() != null) {
            // Clear existing ingredients
            existing.getRecipeIngredients().clear();

            // Add new ingredients
            for (RecipeIngredientDto ingredientDto : dto.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findById(ingredientDto.getIngId())
                        .orElseThrow(() -> new RuntimeException("Ingredient not found: " + ingredientDto.getIngId()));

                RecipeIngredient ri = new RecipeIngredient();
                ri.setRecipe(existing);
                ri.setIngredient(ingredient);
                ri.setQuantity(ingredientDto.getQuantity());
                ri.setUnit(ingredientDto.getUnit());
                existing.getRecipeIngredients().add(ri);
            }
        }

        return repository.save(existing);
    }

    public void deleteRecipe(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Recipe not found");
        }
        repository.deleteById(id);
    }

    public List<Recipe> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Recipe> filterByCalories(Integer minCalories, Integer maxCalories) {
        return repository.findByCaloriesBetween(minCalories, maxCalories);
    }
}
