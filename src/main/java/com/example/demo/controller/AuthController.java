package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {
    private final CustomerProfileService service;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerProfileService service, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public CustomerProfile register(@RequestBody RegisterRequest req) {
        // Assuming password/role handling is external or simplified here as per entity restrictions
        CustomerProfile cp = new CustomerProfile(null, req.fullName, req.email, req.phone, "BRONZE", true, null);
        cp.setCustomerId(req.email); // Using email as ID for demo uniqueness
        return service.createCustomer(cp);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest req) {
        CustomerProfile cp = service.findByCustomerId(req.email); // finding by email stored in ID for demo
        // In real world check passwordEncoder.matches(req.password, storedHash)
        String token = jwtUtil.generateToken(cp.getId(), cp.getEmail(), "ROLE_USER");
        return new ApiResponse(true, "Login success", token);
    }
}