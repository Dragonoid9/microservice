package com.microservice.AuthService.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface JwtService {

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);

    String generateToken(String username, List<String> roles);

    String generateRefreshToken(String username);
}
