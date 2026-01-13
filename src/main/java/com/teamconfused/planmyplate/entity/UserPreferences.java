package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "user_preferences")
@Data
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pref_id")
    private Integer prefId;
    
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
    
    private String diet;
    
    private String allergies;
    
    private String dislikes;
    
    private Integer servings;
    
    private BigDecimal budget;
}
