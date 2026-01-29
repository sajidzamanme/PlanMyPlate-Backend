package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.GroceryListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryListItemRepository extends JpaRepository<GroceryListItem, Integer> {
}
