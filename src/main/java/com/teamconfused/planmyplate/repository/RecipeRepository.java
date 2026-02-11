package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(
            value = "SELECT * FROM recipe WHERE LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')",
            nativeQuery = true
    )
    List<Recipe> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query(
            value = "SELECT * FROM recipe WHERE calories BETWEEN :minCalories AND :maxCalories",
            nativeQuery = true
    )
    List<Recipe> findByCaloriesBetween(@Param("minCalories") Integer minCalories, @Param("maxCalories") Integer maxCalories);
}

