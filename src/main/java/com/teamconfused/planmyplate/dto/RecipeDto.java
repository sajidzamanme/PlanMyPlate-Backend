package com.teamconfused.planmyplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class RecipeDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeCreateRequest {
        private String name;
        private String description;
        private Integer calories;
        private String imageUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeResponse {
        private Integer recipeId;
        private String name;
        private String description;
        private Integer calories;
        private String imageUrl;
    }
}
