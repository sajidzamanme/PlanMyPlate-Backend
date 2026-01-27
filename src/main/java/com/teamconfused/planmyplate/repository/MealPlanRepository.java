package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Integer> {
    List<MealPlan> findByUser_UserId(Integer userId);
    List<MealPlan> findByUser_UserIdAndStatus(Integer userId, String status);
    List<MealPlan> findByUser_UserIdAndDuration(Integer userId, Integer duration);
}
