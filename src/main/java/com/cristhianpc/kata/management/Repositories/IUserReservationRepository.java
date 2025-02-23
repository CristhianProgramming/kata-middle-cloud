package com.cristhianpc.kata.management.Repositories;

import com.cristhianpc.kata.management.Models.Movie;
import com.cristhianpc.kata.management.Models.Reservation;
import com.cristhianpc.kata.management.Models.Room;
import com.cristhianpc.kata.management.Models.UserRevervation;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface IUserReservationRepository extends JpaRepository<UserRevervation, Long> {

    Page<UserRevervation> getAllByReservation(PageRequest pageRequest, Reservation id);

    @Query(nativeQuery = true,value = "SELECT * FROM public.user_reservations WHERE reservation_id = ?1 AND seat = ?2 ; ")
    UserRevervation finAllByAllByReservationAndSeat(Long id_reservation,Integer seat_number);
}
