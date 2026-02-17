package com.teamconfused.planmyplate.dto;

import com.teamconfused.planmyplate.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientQuantityDto {
  private Ingredient ingredient;
  private Integer quantity;
  private String unit;
}
