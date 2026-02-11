package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {

    @Query(
            value = "SELECT * FROM diet WHERE diet_name = :dietName",
            nativeQuery = true
    )
    Optional<Diet> findByDietName(@Param("dietName") String dietName);
}
