package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.*;
import com.teamconfused.planmyplate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository repository;
    
    public List<Ingredient> getAll() {
        return repository.findAll();
    }
    
    public Ingredient getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }
    
    public Ingredient create(Ingredient ingredient) {
        return repository.save(ingredient);
    }
    
    public Ingredient update(Integer id, Ingredient ingredient) {
        if (!repository.existsById(id)) throw new RuntimeException("Ingredient not found");
        return repository.save(ingredient);
    }
    
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
