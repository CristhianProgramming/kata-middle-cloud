package com.cristhianpc.kata.management.Utils;

import com.cristhianpc.kata.management.Models.Users;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Map;

public interface IJwtService {

    String extractUsername(String token);

    String generateToken(Map<String, Object> extraClaims , Users userDetails);

    String generatedToken(Map<String, Object> claims, Users userDetails);

    boolean isTokenValid(String token,UserDetails userDetails);
}
