package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Integer> {

    @Query(
            value = "SELECT * FROM meal_plan WHERE user_id = :userId",
            nativeQuery = true
    )
    List<MealPlan> findByUser_UserId(@Param("userId") Integer userId);

    @Query(
            value = "SELECT * FROM meal_plan WHERE user_id = :userId AND status = :status",
            nativeQuery = true
    )
    List<MealPlan> findByUser_UserIdAndStatus(@Param("userId") Integer userId, @Param("status") String status);

    @Query(
            value = "SELECT * FROM meal_plan WHERE user_id = :userId AND duration = :duration",
            nativeQuery = true
    )
    List<MealPlan> findByUser_UserIdAndDuration(@Param("userId") Integer userId, @Param("duration") Integer duration);
}
