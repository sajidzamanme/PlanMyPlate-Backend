package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            value = "SELECT * FROM user WHERE email = :email",
            nativeQuery = true
    )
    Optional<User> findByEmail(@Param("email") String email);

    @Query(
            value = "SELECT * FROM user WHERE reset_token = :resetToken",
            nativeQuery = true
    )
    Optional<User> findByResetToken(@Param("resetToken") String resetToken);
}
