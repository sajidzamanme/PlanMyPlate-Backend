package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameContainingIgnoreCase(String name);
    List<Ingredient> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    Optional<Ingredient> findByName(String name);
}
