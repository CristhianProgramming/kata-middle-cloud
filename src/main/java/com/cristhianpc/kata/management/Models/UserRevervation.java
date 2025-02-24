package com.cristhianpc.kata.management.Models;

import jakarta.persistence.*;
import lombok.*;

@Table(
        name = "user_reservations",
        uniqueConstraints = {@UniqueConstraint(
        columnNames = {"reservator", "reservation", "seat"}
)})
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRevervation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Users reservator;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Reservation reservation;

    private Integer seat;
}
