package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {

    @Query(value = "SELECT * FROM recipe_ingredients", nativeQuery = true)
    List<RecipeIngredient> findAllIngredients();
}
