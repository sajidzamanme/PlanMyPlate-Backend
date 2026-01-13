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
        return repository.save(recipe);
    }
    
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
