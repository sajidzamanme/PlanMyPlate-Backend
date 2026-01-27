package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.entity.Recipe;
import com.teamconfused.planmyplate.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService service;
    
    @GetMapping
    public ResponseEntity<List<Recipe>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @PostMapping
    public ResponseEntity<Recipe> create(@RequestBody Recipe recipe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(recipe));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable Integer id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(service.update(id, recipe));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Recipe deleted successfully"));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
    
    @GetMapping("/filter/calories")
    public ResponseEntity<List<Recipe>> filterByCalories(@RequestParam Integer minCalories, @RequestParam Integer maxCalories) {
        return ResponseEntity.ok(service.filterByCalories(minCalories, maxCalories));
    }
}

