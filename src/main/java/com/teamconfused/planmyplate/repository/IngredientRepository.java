package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query(
            value = "SELECT * FROM ingredient WHERE LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')",
            nativeQuery = true
    )
    List<Ingredient> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query(
            value = "SELECT * FROM ingredient WHERE price BETWEEN :minPrice AND :maxPrice",
            nativeQuery = true
    )
    List<Ingredient> findByPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    @Query(
            value = "SELECT * FROM ingredient WHERE name = :name",
            nativeQuery = true
    )
    Optional<Ingredient> findByName(@Param("name") String name);
}

