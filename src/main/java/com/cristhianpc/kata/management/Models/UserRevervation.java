package com.cristhianpc.kata.management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(
        name = "user_reservations",
        uniqueConstraints = {@UniqueConstraint(
        columnNames = {"reservator", "reservation", "seat"}
)})
@Entity
@Getter
@Setter
public class UserRevervation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Users reservator;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Reservation reservation;

    private Integer seat;

    public UserRevervation() {
    }

    public UserRevervation(Users reservator, Reservation reservation, Integer seat) {
        this.reservator = reservator;
        this.reservation = reservation;
        this.seat = seat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getReservator() {
        return reservator;
    }

    public void setReservator(Users reservator) {
        this.reservator = reservator;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
