package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "inv_item")
@Data
public class InvItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    private Integer quantity;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "inv_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "ing_id")
    private Ingredient ingredient;
}
