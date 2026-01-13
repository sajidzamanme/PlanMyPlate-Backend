package com.teamconfused.planmyplate.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class EntityDto {
    
    @Data
    public static class IngredientDto {
        private Integer ingId;
        private String name;
        private BigDecimal price;
        private String prefTags;
    }
    
    @Data
    public static class RecipeDto {
        private Integer recipeId;
        private String name;
        private String description;
        private Integer calories;
        private Set<Integer> ingredientIds;
    }
    
    @Data
    public static class InventoryDto {
        private Integer invId;
        private LocalDate lastUpdate;
        private Integer userId;
    }
    
    @Data
    public static class InvItemDto {
        private Integer itemId;
        private Integer quantity;
        private LocalDate dateAdded;
        private LocalDate expiryDate;
        private Integer invId;
        private Integer ingId;
    }
    
    @Data
    public static class MealPlanDto {
        private Integer mpId;
        private LocalDate startDate;
        private Integer duration;
        private String status;
        private Integer userId;
        private Set<Integer> recipeIds;
    }
    
    @Data
    public static class GroceryListDto {
        private Integer listId;
        private LocalDate dateCreated;
        private String status;
        private Integer userId;
        private Set<Integer> ingredientIds;
    }
}
