package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.entity.Allergy;
import com.teamconfused.planmyplate.entity.Diet;
import com.teamconfused.planmyplate.entity.Ingredient;
import com.teamconfused.planmyplate.service.AllergyService;
import com.teamconfused.planmyplate.service.DietService;
import com.teamconfused.planmyplate.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reference-data")
@RequiredArgsConstructor
public class ReferenceDataController {

  private final DietService dietService;
  private final AllergyService allergyService;
  private final IngredientService ingredientService;

  @GetMapping("/diets")
  public ResponseEntity<List<Diet>> getAllDiets() {
    return ResponseEntity.ok(dietService.getAll());
  }

  @GetMapping("/allergies")
  public ResponseEntity<List<Allergy>> getAllAllergies() {
    return ResponseEntity.ok(allergyService.getAll());
  }

  @GetMapping("/dislikes")
  public ResponseEntity<List<Ingredient>> getAllDislikes() {
    // Dislikes are essentially ingredients that users can choose to avoid
    return ResponseEntity.ok(ingredientService.getAll());
  }
}
