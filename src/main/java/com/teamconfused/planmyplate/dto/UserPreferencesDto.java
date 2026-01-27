package com.teamconfused.planmyplate.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class UserPreferencesDto {
    private Integer prefId;
    private Integer userId;
    private String diet;
    private List<String> allergies;
    private List<String> dislikes;
    private Integer servings;
    private BigDecimal budget;
}
