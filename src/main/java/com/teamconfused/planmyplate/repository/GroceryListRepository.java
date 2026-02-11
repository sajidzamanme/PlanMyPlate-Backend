package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {

    @Query(
            value = "SELECT * FROM grocery_list WHERE user_id = :userId",
            nativeQuery = true
    )
    List<GroceryList> findByUser_UserId(@Param("userId") Integer userId);

    @Query(
            value = "SELECT * FROM grocery_list WHERE user_id = :userId AND status = :status",
            nativeQuery = true
    )
    List<GroceryList> findByUser_UserIdAndStatus(@Param("userId") Integer userId, @Param("status") String status);
}
