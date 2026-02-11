package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.dto.AiRecipeRequestDto;
import com.teamconfused.planmyplate.entity.Recipe;
import com.teamconfused.planmyplate.service.GeminiAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiRecipeController {

  private final GeminiAiService geminiAiService;

  @PostMapping("/generate-recipe")
  public ResponseEntity<Recipe> generateRecipe(@Valid @RequestBody AiRecipeRequestDto request) {
    Recipe generatedRecipe = geminiAiService.generateRecipe(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(generatedRecipe);
  }
}
