package com.cristhianpc.kata.management.Controller;

import com.cristhianpc.kata.management.Dto.UserReservation.RequestUserReservation;
import com.cristhianpc.kata.management.Models.UserRevervation;
import com.cristhianpc.kata.management.Services.IUserReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/reservations")
public class UserReservationsController {

    private final IUserReservationService userReservationService;

    public UserReservationsController(IUserReservationService userReservationService) {
        this.userReservationService = userReservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<UserRevervation>> getAllReservationsRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit,
            @PathVariable Long id
    ) throws Exception {
        Page<UserRevervation> userResevations = userReservationService.getAllByReservation(PageRequest.of(page, limit), id);
        return ResponseEntity.ok(userResevations);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserRevervation> getReservationById(
            @PathVariable Long id
    ) {
        UserRevervation userResevations = userReservationService.getReservationById(id);
        return ResponseEntity.ok(userResevations);
    }

    @PostMapping("")
    public ResponseEntity<UserRevervation> createReservation(
            @RequestBody RequestUserReservation reservation
    ) throws Exception {
        UserRevervation userRevervation = userReservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRevervation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRevervation> updateReservation(
            @RequestBody RequestUserReservation reservation,
            @PathVariable Long id
    ) throws Exception {
        UserRevervation userRevervation = userReservationService.updateReservation(id, reservation);
        return ResponseEntity.status(HttpStatus.OK).body(userRevervation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(
            @PathVariable Long id
    ) throws Exception {
        userReservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
