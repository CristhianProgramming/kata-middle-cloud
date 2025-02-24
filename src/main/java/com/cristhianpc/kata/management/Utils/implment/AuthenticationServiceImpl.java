package com.cristhianpc.kata.management.Utils.implment;

import com.cristhianpc.kata.management.Dto.Auth.AuthRequest;
import com.cristhianpc.kata.management.Dto.Auth.AuthResponse;
import com.cristhianpc.kata.management.Models.Users;
import com.cristhianpc.kata.management.Models.enums.UserRols;
import com.cristhianpc.kata.management.Services.IUserService;
import com.cristhianpc.kata.management.Utils.IAuthenticationService;
import com.cristhianpc.kata.management.Utils.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserService userService;
    private final PasswordEncoder encoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(IUserService userService, PasswordEncoder encoder, IJwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userService.createUser(user);
        Map<String, Object> auths = new HashMap<>();
        auths.put("Rol",new Auths(user.getRol()));
        var jwtToken = jwtService.generatedToken(auths,user);
        return new AuthResponse(jwtToken);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userService.getUserByEmail(request.getEmail());
        Map<String, Object> auths = new HashMap<>();
        auths.put("Rol",new Auths(user.getRol()));
        var jwtToken = jwtService.generatedToken(auths,user);
        return new AuthResponse(jwtToken);
    }

    record Auths(UserRols[] userRols){}
}
