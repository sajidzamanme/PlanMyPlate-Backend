package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
}
