package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
