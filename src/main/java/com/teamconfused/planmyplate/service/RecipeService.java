package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.Recipe;
import com.teamconfused.planmyplate.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository repository;
    
    public List<Recipe> getAll() {
        return repository.findAll();
    }
    
    public Recipe getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }
    
    public Recipe create(Recipe recipe) {
        return repository.save(recipe);
    }
    
    public Recipe update(Integer id, Recipe recipe) {
        if (!repository.existsById(id)) throw new RuntimeException("Recipe not found");
        Recipe existing = repository.findById(id).get();
        if (recipe.getName() != null) existing.setName(recipe.getName());
        if (recipe.getDescription() != null) existing.setDescription(recipe.getDescription());
        if (recipe.getCalories() != null) existing.setCalories(recipe.getCalories());
        if (recipe.getIngredients() != null) existing.setIngredients(recipe.getIngredients());
        return repository.save(existing);
    }
    
    public void delete(Integer id) {
        if (!repository.existsById(id)) throw new RuntimeException("Recipe not found");
        repository.deleteById(id);
    }
    
    public List<Recipe> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Recipe> filterByCalories(Integer minCalories, Integer maxCalories) {
        return repository.findByCaloriesBetween(minCalories, maxCalories);
    }
}

