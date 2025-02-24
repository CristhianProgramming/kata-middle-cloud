package com.cristhianpc.kata.management.Dto.Reservation;

import java.time.LocalTime;

public class RequestReservation {
    private Long movie;
    private Long sala;
    private LocalTime time;

    public RequestReservation() {
    }

    public RequestReservation(Long movie, Long sala, LocalTime time) {
        this.movie = movie;
        this.sala = sala;
        this.time = time;
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    public Long getRoom() {
        return sala;
    }

    public void setRoom(Long room) {
        this.sala = room;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
