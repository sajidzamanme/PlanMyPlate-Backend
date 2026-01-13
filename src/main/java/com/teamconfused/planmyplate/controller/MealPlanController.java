package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.entity.MealPlan;
import com.teamconfused.planmyplate.service.MealPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/meal-plans")
@RequiredArgsConstructor
public class MealPlanController {
    private final MealPlanService service;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealPlan>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }
    
    @PostMapping
    public ResponseEntity<MealPlan> create(@RequestBody MealPlan mealPlan) {
        return ResponseEntity.ok(service.create(mealPlan));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MealPlan> update(@PathVariable Integer id, @RequestBody MealPlan mealPlan) {
        return ResponseEntity.ok(service.update(id, mealPlan));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
