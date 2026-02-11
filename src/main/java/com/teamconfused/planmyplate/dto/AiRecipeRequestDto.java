package com.teamconfused.planmyplate.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AiRecipeRequestDto {

  private List<String> availableIngredients;

  @Min(value = 50, message = "Calories must be at least 50")
  @Max(value = 5000, message = "Calories must not exceed 5000")
  private Integer maxCalories;

  private String cuisineType; // e.g., Italian, Indian, Mexican, Chinese, American

  private List<String> allergies;

  private String dietaryPreference; // e.g., Vegan, Vegetarian, Keto, Paleo, None

  private String mood; // e.g., Comfort Food, Quick & Easy, Fancy Dinner, Healthy

  @NotNull(message = "Servings is required")
  @Min(value = 1, message = "Servings must be at least 1")
  @Max(value = 20, message = "Servings must not exceed 20")
  private Integer servings = 4;

  @Min(value = 5, message = "Cooking time must be at least 5 minutes")
  @Max(value = 300, message = "Cooking time must not exceed 300 minutes")
  private Integer maxCookingTime; // in minutes
}
