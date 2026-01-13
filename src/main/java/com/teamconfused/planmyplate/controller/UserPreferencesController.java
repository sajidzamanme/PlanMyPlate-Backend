package com.teamconfused.planmyplate.controller;

import com.teamconfused.planmyplate.dto.UserPreferencesDto;
import com.teamconfused.planmyplate.service.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-preferences")
@RequiredArgsConstructor
public class UserPreferencesController {
    private final UserPreferencesService service;
    
    @PostMapping("/{userId}")
    public ResponseEntity<UserPreferencesDto> setPreferences(
            @PathVariable Integer userId,
            @RequestBody UserPreferencesDto dto) {
        return ResponseEntity.ok(service.setPreferences(userId, dto));
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserPreferencesDto> getPreferences(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getPreferences(userId));
    }
}
