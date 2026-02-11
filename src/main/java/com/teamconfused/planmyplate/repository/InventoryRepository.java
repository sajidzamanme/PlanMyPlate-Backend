package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query(
            value = "SELECT * FROM inventory WHERE user_id = :userId",
            nativeQuery = true
    )
    Optional<Inventory> findByUser_UserId(@Param("userId") Integer userId);
}

