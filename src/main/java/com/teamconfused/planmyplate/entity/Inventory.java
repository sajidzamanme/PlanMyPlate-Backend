package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inv_id")
    private Integer invId;
    
    @Column(name = "last_update")
    private LocalDate lastUpdate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
