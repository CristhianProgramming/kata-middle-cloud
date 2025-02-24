package com.cristhianpc.kata.management.Services;

import com.cristhianpc.kata.management.Models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IRoomService {

    Page<Room> getAllRooms(PageRequest pageRequest);

    Room getRoomById(Long id);

    Room createRoom(Room room);

    Room updateRoom(Long id,Room room) throws Exception;

    void deleteRoom(Long id) throws Exception;
}
