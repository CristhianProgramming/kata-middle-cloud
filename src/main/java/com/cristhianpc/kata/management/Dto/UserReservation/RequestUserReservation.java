package com.cristhianpc.kata.management.Dto.UserReservation;

public class RequestUserReservation {

    private Long reservator;
    private Long reservation;
    private Integer seat;

    public RequestUserReservation() {
    }

    public Long getReservator() {
        return reservator;
    }

    public void setReservator(Long reservator) {
        this.reservator = reservator;
    }

    public Long getReservation() {
        return reservation;
    }

    public void setReservation(Long reservation) {
        this.reservation = reservation;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
