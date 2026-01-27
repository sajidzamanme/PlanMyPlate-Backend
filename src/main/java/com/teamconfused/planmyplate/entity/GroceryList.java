package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "grocery_list")
@Data
@EqualsAndHashCode(exclude = "ingredients")
@ToString(exclude = "ingredients")
public class GroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "grocery_list_ingredients", joinColumns = @JoinColumn(name = "list_id"), inverseJoinColumns = @JoinColumn(name = "ing_id"))
    private Set<Ingredient> ingredients;
}
