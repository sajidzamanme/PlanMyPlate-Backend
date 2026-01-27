package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.entity.*;
import com.teamconfused.planmyplate.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService service;
    
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @PostMapping
    public ResponseEntity<Ingredient> create(@RequestBody Ingredient ingredient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(ingredient));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable Integer id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(service.update(id, ingredient));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Ingredient deleted successfully"));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Ingredient>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }
    
    @GetMapping("/price/range")
    public ResponseEntity<List<Ingredient>> filterByPrice(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return ResponseEntity.ok(service.filterByPrice(minPrice, maxPrice));
    }
}
