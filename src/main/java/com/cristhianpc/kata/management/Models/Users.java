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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRols[] getRol() {
        return rol;
    }

    public void setRol(UserRols[] rol) {
        this.rol = rol;
    }
}
