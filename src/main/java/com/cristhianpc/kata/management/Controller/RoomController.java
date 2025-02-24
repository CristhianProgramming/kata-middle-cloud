package com.cristhianpc.kata.management.Controller;

import com.cristhianpc.kata.management.Models.Room;
import com.cristhianpc.kata.management.Services.IRoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("")
    ResponseEntity<Page<Room>> getAllRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        Page<Room> response = roomService.getAllRooms(PageRequest.of(page, limit));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<Room> getRoomById(
            @PathVariable(name = "id") Long id_room
    ) {
        Room response = roomService.getRoomById(id_room);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    ResponseEntity<Room> createMovie(
            @RequestBody Room payload
    ) {
        Room response = roomService.createRoom(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<Room> updateMovie(
            @PathVariable(name = "id") Long id_room,
            @RequestBody Room payload
    ) throws Exception {
        Room response = roomService.updateRoom(id_room, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMovie(
            @PathVariable(name = "id") Long id_room
    ) throws Exception {
        roomService.deleteRoom(id_room);
        return ResponseEntity.noContent().build();
    }
}
