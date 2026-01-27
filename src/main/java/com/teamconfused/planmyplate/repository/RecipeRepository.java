package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByNameContainingIgnoreCase(String name);
    List<Recipe> findByCaloriesBetween(Integer minCalories, Integer maxCalories);
}
