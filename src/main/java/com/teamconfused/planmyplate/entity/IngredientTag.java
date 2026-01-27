package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Set;

@Entity
@Table(name = "ingredient_tags")
@Data
@EqualsAndHashCode(exclude = "ingredients")
@ToString(exclude = "ingredients")
public class IngredientTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Set<Ingredient> ingredients;
}
