package com.teamconfused.planmyplate.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Set;

@Entity
@Table(name = "allergies")
@Data
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id")
    private Integer allergyId;

    @Column(name = "allergy_name", nullable = false)
    private String allergyName;

    @ManyToMany(mappedBy = "allergies")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Set<User> users;
}
