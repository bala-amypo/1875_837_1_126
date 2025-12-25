package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // In production, use a secure key from properties
    private final String SECRET = "mysecretkeymustbelongenoughforhmacsha256security";
    private final long EXPIRATION = 86400000; // 1 day
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(Long customerId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("customerId", customerId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return validateToken(token).getSubject();
    }
    
    public String extractRole(String token) {
        return validateToken(token).get("role", String.class);
    }
}