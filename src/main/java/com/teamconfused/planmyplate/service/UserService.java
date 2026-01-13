package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.dto.UserDto;
import com.teamconfused.planmyplate.entity.User;
import com.teamconfused.planmyplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserDto getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return new UserDto(user.getUserId(), user.getEmail(), user.getName(), user.getUserName());
    }
}
