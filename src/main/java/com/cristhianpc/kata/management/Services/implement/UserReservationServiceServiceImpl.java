package com.cristhianpc.kata.management.Services.implement;

import com.cristhianpc.kata.management.Dto.UserReservation.RequestUserReservation;
import com.cristhianpc.kata.management.Models.Reservation;
import com.cristhianpc.kata.management.Models.UserRevervation;
import com.cristhianpc.kata.management.Models.Users;
import com.cristhianpc.kata.management.Repositories.IUserRepository;
import com.cristhianpc.kata.management.Repositories.IUserReservationRepository;
import com.cristhianpc.kata.management.Services.IReservationsService;
import com.cristhianpc.kata.management.Services.IUserReservationService;
import com.cristhianpc.kata.management.Utils.IEmailService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserReservationServiceServiceImpl implements IUserReservationService {

    private final IEmailService emailService;
    private final IUserReservationRepository userReservation;
    private final IReservationsService reservationsService;
    private final IUserRepository userRepository;

    public UserReservationServiceServiceImpl(IEmailService emailService, IUserReservationRepository userReservation, IReservationsService reservationsService, IUserRepository userRepository) {
        this.emailService = emailService;
        this.userReservation = userReservation;
        this.reservationsService = reservationsService;
        this.userRepository = userRepository;
    }

    @Override
    public Page<?> getAllByReservation(PageRequest pageRequest, Long id) throws Exception {
        Reservation reservation = reservationsService.getReservationById(id);
        if (reservation == null) {
            throw new Exception("reservation is invalid");
        }
        return userReservation.getAllByReservation(pageRequest, reservation);
    }

    @Override
    public UserRevervation getReservationById(Long id) {
        return userReservation.findById(id).orElse(null);
    }

    @Override
    public UserRevervation createReservation(RequestUserReservation reservation) throws Exception {
        Pair<Users, Reservation> reservationPair = validateReservation(reservation);
        validateSeat(reservationPair.b, reservation.getSeat());
        try {
            UserRevervation createdResponse = userReservation.save(
                    new UserRevervation(
                            reservationPair.a,
                            reservationPair.b,
                            reservation.getSeat()
                    )
            );
            emailService.sendEmailTicket(reservationPair.a.getEmail(),reservation.getSeat());
            return createdResponse;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public UserRevervation updateReservation(Long id, RequestUserReservation reservation) throws Exception {
        UserRevervation userRevervation = getReservationById(id);
        if (userRevervation == null) {
            throw new Exception("reservation is not found");
        }
        try {
            Pair<Users, Reservation> reservationPair = validateReservation(reservation);
            validateSeat(reservationPair.b, reservation.getSeat());
            userRevervation.setReservation(reservationPair.b);
            userRevervation.setReservator(reservationPair.a);
            userRevervation.setSeat(reservation.getSeat());
            UserRevervation createdResponse = userReservation.save(
                    new UserRevervation(
                            reservationPair.a,
                            reservationPair.b,
                            reservation.getSeat()
                    )
            );
            emailService.sendEmailTicket(reservationPair.a.getEmail(),reservation.getSeat());
            return createdResponse;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void deleteReservation(Long id) throws Exception {
        UserRevervation userRevervation = userReservation.getReferenceById(id);
        if (userRevervation.getId() == null) {
            throw new Exception("reservation is not found");
        }
        userReservation.deleteById(id);
    }

    public Pair<Users, Reservation> validateReservation(RequestUserReservation reservation) throws Exception {
        Users reservatorUser = userRepository.findByEmail(reservation.getReservator()).orElse(null);
        Reservation reservationRoom = reservationsService.getReservationById(reservation.getReservation());
        if (reservationRoom == null || reservatorUser == null) {
            throw new Exception("invalid room or user");
        }
        return new Pair<>(reservatorUser, reservationRoom);
    }

    private void validateSeat(Reservation reservation, Integer seat) throws Exception {
        if (reservation.getSala().getCapacity() < seat) {
            throw new Exception("the seat selected is invalid");
        }
        UserRevervation isReserved = userReservation.finAllByAllByReservationAndSeat(reservation.getId(), seat);
        if (isReserved != null) {
            throw new Exception("the seat selected is not available");
        }
    }
}
