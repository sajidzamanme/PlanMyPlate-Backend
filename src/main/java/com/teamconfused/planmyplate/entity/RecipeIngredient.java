package com.teamconfused.planmyplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "recipe_ingredients")
@Data
@ToString(exclude = "recipe")
public class RecipeIngredient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "recipe_id")
  @JsonIgnore
  private Recipe recipe;

  @ManyToOne
  @JoinColumn(name = "ing_id")
  private Ingredient ingredient;

  private Integer quantity;

  private String unit;
}
