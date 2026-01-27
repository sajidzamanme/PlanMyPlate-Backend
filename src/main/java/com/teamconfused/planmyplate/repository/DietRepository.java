package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {
    Optional<Diet> findByDietName(String dietName);
}
