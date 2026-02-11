package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.*;
import com.teamconfused.planmyplate.exception.ResourceNotFoundException;
import com.teamconfused.planmyplate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository repository;

    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    public Ingredient getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingredient", id));
    }

    public Ingredient create(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Ingredient update(Integer id, Ingredient ingredient) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Ingredient", id);
        Ingredient existing = repository.findById(id).get();
        if (ingredient.getName() != null)
            existing.setName(ingredient.getName());
        if (ingredient.getPrice() != null)
            existing.setPrice(ingredient.getPrice());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Ingredient", id);
        repository.deleteById(id);
    }

    public List<Ingredient> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Ingredient> filterByPrice(Double minPrice, Double maxPrice) {
        return repository.findByPriceBetween(BigDecimal.valueOf(minPrice), BigDecimal.valueOf(maxPrice));
    }
}
