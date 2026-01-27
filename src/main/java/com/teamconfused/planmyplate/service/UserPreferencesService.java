package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.dto.UserPreferencesDto;
import com.teamconfused.planmyplate.entity.*;
import com.teamconfused.planmyplate.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPreferencesService {
    private final UserPreferencesRepository preferencesRepository;
    private final UserRepository userRepository;
    private final DietRepository dietRepository;
    private final AllergyRepository allergyRepository;
    private final IngredientRepository ingredientRepository;

    public UserPreferencesDto setPreferences(Integer userId, UserPreferencesDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserPreferences preferences = preferencesRepository.findByUser_UserId(userId)
                .orElse(new UserPreferences());

        preferences.setUser(user);

        // Convert diet name to Diet entity
        if (dto.getDiet() != null && !dto.getDiet().isEmpty()) {
            Diet diet = dietRepository.findByDietName(dto.getDiet())
                    .orElse(null);
            preferences.setDiet(diet);
        }

        // Handle allergies
        if (dto.getAllergies() != null && !dto.getAllergies().isEmpty()) {
            preferences.setAllergies(dto.getAllergies().stream()
                    .map(name -> allergyRepository.findByAllergyName(name.trim()).orElse(null))
                    .filter(a -> a != null)
                    .collect(Collectors.toSet()));
        }

        // Handle dislikes
        if (dto.getDislikes() != null && !dto.getDislikes().isEmpty()) {
            preferences.setDislikes(dto.getDislikes().stream()
                    .map(name -> ingredientRepository.findByName(name.trim()).orElse(null))
                    .filter(i -> i != null)
                    .collect(Collectors.toSet()));
        }

        preferences.setServings(dto.getServings());
        preferences.setBudget(dto.getBudget());

        UserPreferences saved = preferencesRepository.save(preferences);
        return toDto(saved);
    }

    public UserPreferencesDto getPreferences(Integer userId) {
        UserPreferences preferences = preferencesRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));
        return toDto(preferences);
    }

    private UserPreferencesDto toDto(UserPreferences preferences) {
        UserPreferencesDto dto = new UserPreferencesDto();
        dto.setPrefId(preferences.getPrefId());
        dto.setUserId(preferences.getUser().getUserId());

        // Convert Diet entity to diet name
        if (preferences.getDiet() != null) {
            dto.setDiet(preferences.getDiet().getDietName());
        }

        // Convert allergies set to list
        if (preferences.getAllergies() != null && !preferences.getAllergies().isEmpty()) {
            java.util.List<String> allergiesList = preferences.getAllergies().stream()
                    .map(Allergy::getAllergyName)
                    .collect(Collectors.toList());
            dto.setAllergies(allergiesList);
        }

        // Convert dislikes set to list
        if (preferences.getDislikes() != null && !preferences.getDislikes().isEmpty()) {
            java.util.List<String> dislikesList = preferences.getDislikes().stream()
                    .map(Ingredient::getName)
                    .collect(Collectors.toList());
            dto.setDislikes(dislikesList);
        }

        dto.setServings(preferences.getServings());
        dto.setBudget(preferences.getBudget());
        return dto;
    }
}
