package com.cristhianpc.kata.management.Models;

import com.cristhianpc.kata.management.Models.enums.CLASSIFICATION;
import com.cristhianpc.kata.management.Models.enums.GendersMovie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "movies")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private GendersMovie gender;

    private String imageUrl;

    private Integer duration;

    private CLASSIFICATION classification;

}
