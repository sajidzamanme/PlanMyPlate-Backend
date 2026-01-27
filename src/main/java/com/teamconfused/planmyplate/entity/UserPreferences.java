package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "user_preferences")
@Data
@EqualsAndHashCode(exclude = { "allergies", "dislikes" })
@ToString(exclude = { "allergies", "dislikes" })
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pref_id")
    private Integer prefId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @ManyToMany
    @JoinTable(name = "user_preferences_allergies", joinColumns = @JoinColumn(name = "pref_id"), inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private Set<Allergy> allergies;

    @ManyToMany
    @JoinTable(name = "user_preferences_dislikes", joinColumns = @JoinColumn(name = "pref_id"), inverseJoinColumns = @JoinColumn(name = "ing_id"))
    private Set<Ingredient> dislikes;

    private Integer servings;

    private BigDecimal budget;
}
