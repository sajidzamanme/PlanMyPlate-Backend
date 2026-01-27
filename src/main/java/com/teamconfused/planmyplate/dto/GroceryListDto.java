package com.teamconfused.planmyplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class GroceryListDto {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GroceryListCreateRequest {
        private String status;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GroceryListResponse {
        private Integer listId;
        private LocalDate dateCreated;
        private String status;
        private Integer userId;
    }
}
