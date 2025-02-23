package com.cristhianpc.kata.management.Models;

import com.cristhianpc.kata.management.Models.enums.CLASSIFICATION;
import com.cristhianpc.kata.management.Models.enums.GendersMovie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "movies")
@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private GendersMovie gender;

    private Integer duration;

    private CLASSIFICATION classification;

    public Movie() {
    }

    public Movie(Long id, GendersMovie gender, Integer duration, CLASSIFICATION classification) {
        this.id = id;
        this.gender = gender;
        this.duration = duration;
        this.classification = classification;
    }
}
