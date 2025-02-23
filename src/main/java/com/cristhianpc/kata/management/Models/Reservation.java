package com.cristhianpc.kata.management.Models;

import jakarta.persistence.*;

import java.time.LocalTime;

@Table(name = "reservation")
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Room sala;

    private LocalTime time;



}
