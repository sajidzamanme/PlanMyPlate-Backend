package com.teamconfused.planmyplate.dto;

import lombok.Data;

@Data
public class UpdateItemRequestDto {
  private Integer quantity;
  private String unit;
  private java.time.LocalDate expiryDate; // For inventory updates
}
