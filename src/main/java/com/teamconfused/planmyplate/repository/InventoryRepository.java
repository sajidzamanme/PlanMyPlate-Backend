package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<Inventory> findByUser_UserId(Integer userId);
}
