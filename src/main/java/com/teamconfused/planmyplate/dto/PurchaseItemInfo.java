package com.teamconfused.planmyplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemInfo {
  private Integer itemId;
  private Integer quantity;
}
