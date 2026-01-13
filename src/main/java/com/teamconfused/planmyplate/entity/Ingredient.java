package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ing_id")
    private Integer ingId;
    
    private String name;
    
    private BigDecimal price;
    
    @Column(name = "pref_tags")
    private String prefTags;
}
