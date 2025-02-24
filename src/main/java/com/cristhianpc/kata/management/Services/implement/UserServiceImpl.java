package com.cristhianpc.kata.management.Services.implement;

import com.cristhianpc.kata.management.Models.Users;
import com.cristhianpc.kata.management.Models.enums.UserRols;
import com.cristhianpc.kata.management.Repositories.IUserRepository;
import com.cristhianpc.kata.management.Services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, IUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public Page<?> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Users createUser(Users users) {
        UserRols[] rol = {UserRols.USER};
        users.setRol(rol);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    @Override
    public Users editUser(Long id, Users payload) {
        Users userExist = userRepository.findById(id).orElseThrow();
        userExist.setEmail(payload.getEmail());
        userExist.setPassword(passwordEncoder.encode(payload.getPassword()));
        return userRepository.save(userExist);
    }

    @Override
    public void deleteUser(Long id) {
        Users userExist = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }

}
