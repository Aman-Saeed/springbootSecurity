package com.springboot.springbootSecurity.services;

import com.springboot.springbootSecurity.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecret;

    private SecretKey getSecretKey() {
        // Convert the secret key string to a SecretKey object
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        String subject = user.getEmail(); // Use the user's email as the subject of the token
        // Generate a JWT token using the secret key and the subject (e.g., username)
        return Jwts.builder()
                .setSubject(user.getId().toString()) // Use the user's ID as the subject of the token
                .claim("email", user.getEmail()) // Add the user's email as a claim
                .claim("role", Set.of("ADMAIN", "USER")) // Add the user's role as a claim (you can customize this based on your application)
                .issuedAt(new Date()) // Set the issued date of the token
                .setExpiration(new Date(System.currentTimeMillis() + 60000)) // Set expiry for one minute
                .signWith(getSecretKey()) // Sign the token with the secret key
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        // Parse the JWT token and extract the subject (user ID)
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.parseLong(claims.getSubject());
    }
}
