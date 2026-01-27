package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@EqualsAndHashCode(exclude = "tags")
@ToString(exclude = "tags")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ing_id")
    private Integer ingId;

    private String name;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "ingredient_tag_map", joinColumns = @JoinColumn(name = "ing_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<IngredientTag> tags;
}
