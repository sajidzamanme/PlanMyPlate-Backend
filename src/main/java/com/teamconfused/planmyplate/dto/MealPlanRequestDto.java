package com.teamconfused.planmyplate.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class MealPlanRequestDto {
  private List<Integer> recipeIds;
  private Integer duration;
  private LocalDate startDate;
}
