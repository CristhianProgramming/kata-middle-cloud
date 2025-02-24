package com.cristhianpc.kata.management.Controller;

import com.cristhianpc.kata.management.Dto.Reservation.RequestReservation;
import com.cristhianpc.kata.management.Models.Reservation;
import com.cristhianpc.kata.management.Services.IReservationsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final IReservationsService reservationsService;

    public ReservationController(IReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping("")
    ResponseEntity<Page<Reservation>> getAllReservations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int limit) {
        Page<Reservation> response = reservationsService.getAllReservations(PageRequest.of(page, limit));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<Reservation> getReservatonById(@PathVariable Long id ) {
        Reservation response = reservationsService.getReservationById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/room/{id}")
    ResponseEntity<Page<Reservation>> getAllReservationsByRoom(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int limit, @PathVariable(name = "id") Long room_id) {
        Page<Reservation> response = reservationsService.getReservationsByRoom(PageRequest.of(page, limit), room_id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    ResponseEntity<Reservation> createReservation(@RequestBody RequestReservation requestReservation) throws Exception {
        Reservation response = reservationsService.createReservation(requestReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<Reservation> updateReservation(@RequestBody RequestReservation requestReservation, @PathVariable Long id) throws Exception {
        Reservation response = reservationsService.updateReservation(id, requestReservation);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteReservation(@PathVariable Long id) throws Exception {
        reservationsService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

}
