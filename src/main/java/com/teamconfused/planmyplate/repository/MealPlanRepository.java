package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlan, Integer> {
    List<MealPlan> findByUser_UserId(Integer userId);
}
