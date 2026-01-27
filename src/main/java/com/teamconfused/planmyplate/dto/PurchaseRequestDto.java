package com.teamconfused.planmyplate.dto;

import lombok.Data;
import java.util.List;

@Data
public class PurchaseRequestDto {
  private List<Integer> ingredientIds;
}
