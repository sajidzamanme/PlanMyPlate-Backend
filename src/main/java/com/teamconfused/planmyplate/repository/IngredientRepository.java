package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
