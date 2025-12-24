package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                // Validate token and extract claims
                // Note: The helper doc says "validateToken" returns parsed object, 
                // but checking validity via try/catch is standard if using Jwts.parser().
                // We use the helper methods we added to JwtUtil to get the data.
                
                String email = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractRole(token);
                
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            email, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // If token is invalid or expired, do not set authentication context.
                // Request proceeds unauthenticated (and will be rejected by SecurityConfig if endpoint is protected).
            }
        }
        chain.doFilter(request, response);
    }
}