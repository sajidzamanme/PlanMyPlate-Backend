package com.teamconfused.planmyplate.dto;

import lombok.Data;
import java.util.List;

@Data
public class RecipeCreateDto {
  private String name;
  private String description;
  private Integer calories;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String instructions;
  private String imageUrl;
  private List<RecipeIngredientDto> ingredients;
}
