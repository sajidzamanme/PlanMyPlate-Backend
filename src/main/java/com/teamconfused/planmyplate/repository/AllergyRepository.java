package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Integer> {

    @Query(value = "SELECT * FROM allergies WHERE allergy_name = :allergyName", nativeQuery = true)
    Optional<Allergy> findByAllergyName(@Param("allergyName") String allergyName);
}
