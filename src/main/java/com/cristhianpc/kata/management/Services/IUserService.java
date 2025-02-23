package com.cristhianpc.kata.management.Services;

import com.cristhianpc.kata.management.Models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUserService {

    Page<?> getAllUsers(PageRequest pageRequest);

    Users getUserByEmail(String email);

    Users createUser(Users users);

    Users editUser(Long id, Users users);

    void deleteUser(Long id);
}
