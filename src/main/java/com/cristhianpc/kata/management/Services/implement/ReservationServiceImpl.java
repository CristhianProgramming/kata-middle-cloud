package com.cristhianpc.kata.management.Services.implement;

import com.cristhianpc.kata.management.Dto.Reservation.RequestReservation;
import com.cristhianpc.kata.management.Models.Movie;
import com.cristhianpc.kata.management.Models.Reservation;
import com.cristhianpc.kata.management.Models.Room;
import com.cristhianpc.kata.management.Repositories.IReservationRepository;
import com.cristhianpc.kata.management.Services.IMovieService;
import com.cristhianpc.kata.management.Services.IReservationsService;
import com.cristhianpc.kata.management.Services.IRoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationsService {

    private final IReservationRepository reservationRepository;
    private final IRoomService roomService;
    private final IMovieService movieService;

    public ReservationServiceImpl(IReservationRepository reservationRepository, IRoomService roomService, IMovieService movieService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
        this.movieService = movieService;
    }


    @Override
    public Page<Reservation> getAllReservations(PageRequest pageRequest) {
        return reservationRepository.findAll(pageRequest);
    }

    @Override
    public Page<Reservation> getReservationsByRoom(PageRequest pageRequest, Long id) {
        return reservationRepository.findAllBySala(id, pageRequest);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation createReservation(RequestReservation reservation) throws Exception {
        Movie existMovie = movieService.getMovieByField(reservation.getMovie());
        Room existRoom = roomService.getRoomById(reservation.getSala());

        if (existRoom == null || existMovie == null) {
            throw new Exception("invalid room or movie");
        }

        validateReservationTime(
                reservation.getTime(),
                reservation.getTime().plusMinutes(existMovie.getDuration()),
                existRoom.getId());
        return reservationRepository.save(
                Reservation.builder()
                        .sala(existRoom)
                        .movie(existMovie)
                        .time(reservation.getTime())
                        .build());
    }

    @Override
    public Reservation updateReservation(Long id, RequestReservation reservation) throws Exception {

        Movie existMovie = movieService.getMovieByField(reservation.getMovie());
        Room existRoom = roomService.getRoomById(reservation.getSala());

        if (existRoom == null || existMovie == null) {
            throw new Exception("invalid room or movie");
        }

        Reservation reservationExist = getReservationById(id);

        if (reservationExist == null) {
            throw new Exception("Can't find reservation");
        }

        validateReservationTime(
                reservation.getTime(),
                reservation.getTime().plusMinutes(existMovie.getDuration()),
                existRoom.getId());

        reservationExist.setSala(existRoom);
        reservationExist.setMovie(existMovie);
        reservationExist.setTime(reservation.getTime());

        return reservationRepository.save(reservationExist);
    }

    @Override
    public void deleteReservation(Long id) throws Exception {
        Reservation reservationExist = getReservationById(id);

        if (reservationExist == null) {
            throw new Exception("Can't find reservation");
        }

        reservationRepository.deleteById(id);
    }

    private void validateReservationTime(LocalTime startTime, LocalTime finalTime, Long room_id) throws Exception {
        List<Reservation> moviesWithReservations = reservationRepository.findAllBetwenTimeAndRoomIsEquals(
                startTime.minusHours(3),
                finalTime,
                room_id);

        if (moviesWithReservations.isEmpty()) {
            return;
        }
        moviesWithReservations.forEach(reservation -> {
            LocalTime reservationStart = reservation.getTime();
            LocalTime reservationEnd = reservationStart.plusMinutes(reservation.getMovie().getDuration());

            boolean overlaps =
                    (startTime.isBefore(reservationEnd) && startTime.isAfter(reservationStart)) ||
                            (finalTime.isBefore(reservationEnd) && finalTime.isAfter(reservationStart)) ||
                            (startTime.isBefore(reservationStart) && finalTime.isAfter(reservationEnd)) ||
                            (startTime.equals(reservationStart) || finalTime.equals(reservationEnd));

            if (overlaps) {
                throw new RuntimeException("Invalid hour of assignment: Time is already reserved by " +
                        reservation.getMovie().getName() + " from: " + reservationStart + " to: " + reservationEnd);
            }
        });
    }
}
