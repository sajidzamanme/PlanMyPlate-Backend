package com.teamconfused.planmyplate.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UserPreferencesDto {
    private Integer prefId;
    private Integer userId;
    private String diet;
    private String allergies;
    private String dislikes;
    private Integer servings;
    private BigDecimal budget;
}
