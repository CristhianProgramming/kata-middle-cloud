package com.cristhianpc.kata.management.Utils;

import com.cristhianpc.kata.management.Dto.Auth.AuthRequest;
import com.cristhianpc.kata.management.Dto.Auth.AuthResponse;

public interface IAuthenticationService {

    AuthResponse register(AuthRequest request);

    AuthResponse authenticate(AuthRequest request);

}
