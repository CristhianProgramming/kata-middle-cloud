package com.cristhianpc.kata.management.Dto.Reservation;

import java.time.LocalTime;

public class RequestReservation {
    private Long movie;
    private Long room;
    private LocalTime time;

    public RequestReservation() {
    }

    public RequestReservation(Long movie, Long room, LocalTime time) {
        this.movie = movie;
        this.room = room;
        this.time = time;
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
