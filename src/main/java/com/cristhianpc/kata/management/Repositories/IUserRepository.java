package com.cristhianpc.kata.management.Repositories;

import com.cristhianpc.kata.management.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
}
