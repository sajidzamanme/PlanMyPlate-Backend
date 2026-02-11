package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.dto.UserDto;
import com.teamconfused.planmyplate.entity.User;
import com.teamconfused.planmyplate.exception.ResourceNotFoundException;
import com.teamconfused.planmyplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email: " + email));

        return new UserDto(user.getUserId(), user.getEmail(), user.getName(), user.getUserName());
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    public User updateUser(Integer userId, User userUpdate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        if (userUpdate.getName() != null) {
            user.setName(userUpdate.getName());
        }
        if (userUpdate.getUserName() != null) {
            user.setUserName(userUpdate.getUserName());
        }
        if (userUpdate.getAge() != null) {
            user.setAge(userUpdate.getAge());
        }
        if (userUpdate.getWeight() != null) {
            user.setWeight(userUpdate.getWeight());
        }
        if (userUpdate.getBudget() != null) {
            user.setBudget(userUpdate.getBudget());
        }

        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", userId);
        }
        userRepository.deleteById(userId);
    }
}
