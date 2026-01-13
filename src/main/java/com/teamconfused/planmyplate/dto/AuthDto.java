package com.teamconfused.planmyplate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class AuthDto {
    
    @Data
    public static class SignUpRequest {
        @NotBlank
        private String name;
        
        @Email
        @NotBlank
        private String email;
        
        @NotBlank
        private String password;
    }
    
    @Data
    public static class SignInRequest {
        @Email
        @NotBlank
        private String email;
        
        @NotBlank
        private String password;
    }
    
    @Data
    public static class ForgotPasswordRequest {
        @Email
        @NotBlank
        private String email;
    }
    
    @Data
    public static class AuthResponse {
        private String token;
        private String email;
        private String name;
        
        public AuthResponse(String token, String email, String name) {
            this.token = token;
            this.email = email;
            this.name = name;
        }
    }
    
    @Data
    public static class MessageResponse {
        private String message;
        
        public MessageResponse(String message) {
            this.message = message;
        }
    }
}
