package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Integer> {
    Optional<Allergy> findByAllergyName(String allergyName);
}
