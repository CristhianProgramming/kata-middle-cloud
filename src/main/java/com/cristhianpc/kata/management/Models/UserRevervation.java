package com.cristhianpc.kata.management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames = {"reservator", "reservation", "seat"}
)})
@Entity
@Getter
@Setter
public class UserRevervation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Users reservator;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Reservation reservation;

    private Integer seat;

    public UserRevervation() {
    }

    public UserRevervation(Long id, Users reservator, Reservation reservation, Integer seat) {
        this.id = id;
        this.reservator = reservator;
        this.reservation = reservation;
        this.seat = seat;
    }
}
