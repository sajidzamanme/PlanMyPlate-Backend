package com.teamconfused.planmyplate.repository;

import com.teamconfused.planmyplate.entity.InvItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvItemRepository extends JpaRepository<InvItem, Integer> {

    @Query(
            value = "SELECT * FROM inv_item WHERE inv_id = :invId",
            nativeQuery = true
    )
    List<InvItem> findByInventory_InvId(@Param("invId") Integer invId);
}

