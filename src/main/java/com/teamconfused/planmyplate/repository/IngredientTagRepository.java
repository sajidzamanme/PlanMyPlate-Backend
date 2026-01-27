package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.IngredientTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientTagRepository extends JpaRepository<IngredientTag, Integer> {
    IngredientTag findByTagName(String tagName);
}
