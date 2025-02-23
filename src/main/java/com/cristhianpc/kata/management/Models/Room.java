package com.cristhianpc.kata.management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "salas")
@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer capacity;

    public Room(Long id, String name, Integer capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }
}
