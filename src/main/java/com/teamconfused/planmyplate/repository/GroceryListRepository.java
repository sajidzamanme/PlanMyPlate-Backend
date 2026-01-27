package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {
    List<GroceryList> findByUser_UserId(Integer userId);
    List<GroceryList> findByUser_UserIdAndStatus(Integer userId, String status);
}
