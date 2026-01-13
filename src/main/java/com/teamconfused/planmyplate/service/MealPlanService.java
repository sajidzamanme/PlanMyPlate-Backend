package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.MealPlan;
import com.teamconfused.planmyplate.repository.MealPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlanService {
    private final MealPlanRepository repository;
    
    public List<MealPlan> getByUserId(Integer userId) {
        return repository.findByUser_UserId(userId);
    }
    
    public MealPlan getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Meal plan not found"));
    }
    
    public MealPlan create(MealPlan mealPlan) {
        return repository.save(mealPlan);
    }
    
    public MealPlan update(Integer id, MealPlan mealPlan) {
        if (!repository.existsById(id)) throw new RuntimeException("Meal plan not found");
        return repository.save(mealPlan);
    }
    
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
