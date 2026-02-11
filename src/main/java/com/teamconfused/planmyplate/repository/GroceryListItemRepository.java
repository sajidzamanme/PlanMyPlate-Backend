package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.GroceryListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryListItemRepository extends JpaRepository<GroceryListItem, Integer> {

    @Query(
            value = "SELECT * FROM grocery_list_item",
            nativeQuery = true
    )
    List<GroceryListItem> findAllItems();
}
