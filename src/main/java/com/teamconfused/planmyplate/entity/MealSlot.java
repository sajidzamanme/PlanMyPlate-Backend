package com.teamconfused.planmyplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "meal_slot")
@Data
@ToString(exclude = "mealPlan")
public class MealSlot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "mp_id")
  @JsonIgnore
  private MealPlan mealPlan;

  @ManyToOne
  @JoinColumn(name = "recipe_id")
  private Recipe recipe;

  @Column(name = "slot_index")
  private Integer slotIndex;

  @Column(name = "meal_type")
  private String mealType;

  @Column(name = "day_number")
  private Integer dayNumber;
}
