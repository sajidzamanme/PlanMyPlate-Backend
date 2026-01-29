package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Table(name = "grocery_list")
@Data
@EqualsAndHashCode(exclude = "items")
@ToString(exclude = "items")
public class GroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "groceryList", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<GroceryListItem> items;
}
