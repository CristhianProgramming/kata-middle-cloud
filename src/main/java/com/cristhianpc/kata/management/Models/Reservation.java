package com.cristhianpc.kata.management.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Table(name = "reservation")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Movie movie;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Room sala;

    private LocalTime time;


}
