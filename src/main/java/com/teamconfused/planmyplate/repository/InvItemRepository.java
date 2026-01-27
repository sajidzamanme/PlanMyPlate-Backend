package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.InvItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvItemRepository extends JpaRepository<InvItem, Integer> {
    List<InvItem> findByInventory_InvId(Integer invId);
}
