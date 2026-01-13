package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "user_name")
    private String userName;
    
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private Integer age;
    
    private BigDecimal weight;
    
    private BigDecimal budget;
    
    @Transient
    private String resetToken;
    
    @Transient
    private java.time.LocalDateTime resetTokenExpiry;
}
