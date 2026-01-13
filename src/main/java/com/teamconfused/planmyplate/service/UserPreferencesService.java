package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.dto.UserPreferencesDto;
import com.teamconfused.planmyplate.entity.User;
import com.teamconfused.planmyplate.entity.UserPreferences;
import com.teamconfused.planmyplate.repository.UserPreferencesRepository;
import com.teamconfused.planmyplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferencesService {
    private final UserPreferencesRepository preferencesRepository;
    private final UserRepository userRepository;
    
    public UserPreferencesDto setPreferences(Integer userId, UserPreferencesDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        UserPreferences preferences = preferencesRepository.findByUser_UserId(userId)
                .orElse(new UserPreferences());
        
        preferences.setUser(user);
        preferences.setDiet(dto.getDiet());
        preferences.setAllergies(dto.getAllergies());
        preferences.setDislikes(dto.getDislikes());
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
        dto.setDiet(preferences.getDiet());
        dto.setAllergies(preferences.getAllergies());
        dto.setDislikes(preferences.getDislikes());
        dto.setServings(preferences.getServings());
        dto.setBudget(preferences.getBudget());
        return dto;
    }
}
