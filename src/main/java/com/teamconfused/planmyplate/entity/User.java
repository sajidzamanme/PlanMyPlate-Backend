package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(exclude = { "allergies", "dislikes" })
@ToString(exclude = { "allergies", "dislikes" })
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

    @ManyToMany
    @JoinTable(name = "user_allergies", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private Set<Allergy> allergies;

    @ManyToMany
    @JoinTable(name = "user_dislikes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ing_id"))
    private Set<Ingredient> dislikes;

    @Transient
    private String resetToken;

    @Transient
    private java.time.LocalDateTime resetTokenExpiry;
}
