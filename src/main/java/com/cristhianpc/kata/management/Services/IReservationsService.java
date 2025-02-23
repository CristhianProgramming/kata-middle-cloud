package com.cristhianpc.kata.management.Services;

import com.cristhianpc.kata.management.Dto.Reservation.RequestReservation;
import com.cristhianpc.kata.management.Models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IReservationsService {

   Page<?> getAllReservations(PageRequest pageRequest);
   Page<?> getReservationsByRoom(PageRequest pageRequest,Long id);

   Reservation getReservationById(Long id);
   Reservation createReservation(RequestReservation reservation) throws Exception;
   Reservation updateReservation(Long id,RequestReservation reservation) throws Exception;

   void deleteReservation(Long id) throws Exception;

}
