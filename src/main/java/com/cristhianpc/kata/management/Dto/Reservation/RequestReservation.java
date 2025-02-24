package com.cristhianpc.kata.management.Dto.Reservation;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestReservation {
    private Long movie;
    private Long sala;
    private LocalTime time;
}
