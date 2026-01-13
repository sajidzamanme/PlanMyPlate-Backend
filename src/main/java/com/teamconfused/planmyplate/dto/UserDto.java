package com.teamconfused.planmyplate.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Integer userId;
    private String email;
    private String name;
    private String userName;
    
    public UserDto(Integer userId, String email, String name, String userName) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.userName = userName;
    }
}
