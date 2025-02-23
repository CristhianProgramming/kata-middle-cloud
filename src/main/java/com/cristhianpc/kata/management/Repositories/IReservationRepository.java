package com.cristhianpc.kata.management.Repositories;

import com.cristhianpc.kata.management.Models.Movie;
import com.cristhianpc.kata.management.Models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation,Long> {

    Page<Reservation> findAllBySala(Long id, PageRequest pageRequest);

    @Query(nativeQuery = true,value = "SELECT * FROM public.reservation WHERE time >= ?1 AND time < ?2 AND sala_id = ?3;")
    List<Reservation> findAllBetwenTimeAndRoomIsEquals(LocalTime starTime, LocalTime finalTime,Long idRoom);

}
