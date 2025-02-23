package com.cristhianpc.kata.management.Repositories;

import com.cristhianpc.kata.management.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Long> {
}
