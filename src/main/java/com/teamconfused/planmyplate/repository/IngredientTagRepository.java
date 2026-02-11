package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.IngredientTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientTagRepository extends JpaRepository<IngredientTag, Integer> {

    @Query(
            value = "SELECT * FROM ingredient_tag WHERE tag_name = :tagName",
            nativeQuery = true
    )
    IngredientTag findByTagName(@Param("tagName") String tagName);
}

