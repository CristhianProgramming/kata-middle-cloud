package com.cristhianpc.kata.management.Repositories;

import com.cristhianpc.kata.management.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
}
