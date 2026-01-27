package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "diets")
@Data
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private Integer dietId;
    
    @Column(name = "diet_name", nullable = false)
    private String dietName;
}
