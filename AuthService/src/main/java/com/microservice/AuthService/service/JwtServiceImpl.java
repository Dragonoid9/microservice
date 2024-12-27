package com.microservice.AuthService.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessexpiration}")
    private long expirationTime;

    @Value("${jwt.refreshexpiration}")
    private long refreshExpirationTime;


    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object rolesClaim = claims.get("roles");

        // Check if rolesClaim is an instance of List
        if (rolesClaim instanceof List<?>) {
            // Safely cast it to List<String> if it is indeed a list
            return ((List<?>) rolesClaim).stream()
                    .filter(String.class::isInstance)  // Ensure each element is a String
                    .map(String.class::cast)           // Cast elements to String
                    .collect(Collectors.toList());    // Collect into a List<String>
        }
        // Return an empty list if the claim is not a List or is missing
        return Collections.emptyList();
    }

    private String createToken(Map<String, Object> claims, String username, long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String generateToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();  // Use a Map, not a List
        claims.put("roles", roles);  // Include roles in the token claims
        return createToken(claims, username, expirationTime);
    }

    @Override
    public String generateRefreshToken(String username) {
        return createToken(new HashMap<>(), username, refreshExpirationTime);
    }

}
