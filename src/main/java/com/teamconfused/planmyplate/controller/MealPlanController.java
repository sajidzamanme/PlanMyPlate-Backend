package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.entity.MealPlan;
import com.teamconfused.planmyplate.service.MealPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/user/{userId}")
    public ResponseEntity<MealPlan> create(@PathVariable Integer userId, @RequestBody MealPlan mealPlan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userId, mealPlan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealPlan> update(@PathVariable Integer id, @RequestBody MealPlan mealPlan) {
        return ResponseEntity.ok(service.update(id, mealPlan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Meal plan deleted successfully"));
    }

    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<MealPlan>> getByUserIdAndStatus(@PathVariable Integer userId,
            @PathVariable String status) {
        return ResponseEntity.ok(service.getByUserIdAndStatus(userId, status));
    }

    @GetMapping("/user/{userId}/weekly")
    public ResponseEntity<List<MealPlan>> getWeeklyMealPlans(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getWeeklyMealPlans(userId));
    }

    @PostMapping("/user/{userId}/create")
    public ResponseEntity<MealPlan> create(@PathVariable Integer userId,
            @RequestBody com.teamconfused.planmyplate.dto.MealPlanRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWithRecipes(userId, dto));
    }
}
