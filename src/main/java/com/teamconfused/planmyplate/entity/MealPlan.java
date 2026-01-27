package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "meal_plan")
@Data
@EqualsAndHashCode(exclude = "recipes")
@ToString(exclude = "recipes")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mp_id")
    private Integer mpId;

    @Column(name = "start_date")
    private LocalDate startDate;

    private Integer duration;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "meal_plan_recipe", joinColumns = @JoinColumn(name = "mp_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes;
}
