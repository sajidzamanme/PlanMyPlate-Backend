package com.teamconfused.planmyplate.dto;

import lombok.Data;

@Data
public class RecipeIngredientDto {
  private Integer ingId;
  private Integer quantity;
  private String unit;
}
