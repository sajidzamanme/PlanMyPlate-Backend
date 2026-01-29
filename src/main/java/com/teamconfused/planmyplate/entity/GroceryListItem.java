package com.teamconfused.planmyplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "grocery_list_ingredients")
@Data
@ToString(exclude = "groceryList")
public class GroceryListItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "list_id")
  @JsonIgnore
  private GroceryList groceryList;

  @ManyToOne
  @JoinColumn(name = "ing_id")
  private Ingredient ingredient;

  private Integer quantity;

  private String unit;
}
