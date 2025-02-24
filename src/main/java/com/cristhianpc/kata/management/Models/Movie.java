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

    private String name;

    private String description;

    private GendersMovie gender;

    private String imageUrl;

    private Integer duration;

    private CLASSIFICATION classification;

    public Movie() {
    }

    public Movie(Long id, String name, String description, GendersMovie gender, String imageUrl, Integer duration, CLASSIFICATION classification) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.classification = classification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GendersMovie getGender() {
        return gender;
    }

    public void setGender(GendersMovie gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public CLASSIFICATION getClassification() {
        return classification;
    }

    public void setClassification(CLASSIFICATION classification) {
        this.classification = classification;
    }
}
