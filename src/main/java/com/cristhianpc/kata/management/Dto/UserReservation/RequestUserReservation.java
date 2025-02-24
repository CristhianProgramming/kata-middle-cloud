package com.cristhianpc.kata.management.Dto.UserReservation;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserReservation {

    private String reservator;
    private Long reservation;
    private Integer seat;

}
