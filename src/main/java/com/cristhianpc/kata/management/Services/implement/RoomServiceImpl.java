package com.cristhianpc.kata.management.Services.implement;

import com.cristhianpc.kata.management.Models.Room;
import com.cristhianpc.kata.management.Repositories.IRoomRepository;
import com.cristhianpc.kata.management.Services.IRoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements IRoomService {

    private final IRoomRepository roomRepository;

    public RoomServiceImpl(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<?> getAllRooms(PageRequest pageRequest) {
        return roomRepository.findAll(pageRequest);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) throws Exception {
        Room exist = getRoomById(id);
        if (exist == null) {
            throw new Exception("Room not found");
        }
        exist.setCapacity(room.getCapacity());
        exist.setName(room.getName());

        return roomRepository.save(exist);
    }

    @Override
    public void deleteRoom(Long id) throws Exception {
        Room exist = getRoomById(id);
        if (exist == null) {
            throw new Exception("Room not found");
        }
        roomRepository.deleteById(id);
    }
}
