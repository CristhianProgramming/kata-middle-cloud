package com.cristhianpc.kata.management.Models;

import com.cristhianpc.kata.management.Models.enums.UserRols;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private UserRols[] rol;

    public Users() {
    }

    public Users(Long id, String email, UserRols[] rol) {
        this.id = id;
        this.email = email;
        this.rol = rol;
    }
}
