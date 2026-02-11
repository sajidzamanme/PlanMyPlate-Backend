package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Integer> {

    @Query(
            value = "SELECT * FROM user_preferences WHERE user_id = :userId",
            nativeQuery = true
    )
    Optional<UserPreferences> findByUser_UserId(@Param("userId") Integer userId);
}

