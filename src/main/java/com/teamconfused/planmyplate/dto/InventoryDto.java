package com.teamconfused.planmyplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class InventoryDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InventoryResponse {
        private Integer invId;
        private LocalDate lastUpdate;
        private Integer userId;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvItemCreateRequest {
        private Integer quantity;
        private LocalDate expiryDate;
        private Integer ingId;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvItemResponse {
        private Integer itemId;
        private Integer quantity;
        private LocalDate dateAdded;
        private LocalDate expiryDate;
        private Integer ingId;
    }
}
