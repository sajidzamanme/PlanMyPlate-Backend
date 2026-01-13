package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Integer> {
    Optional<UserPreferences> findByUser_UserId(Integer userId);
}
