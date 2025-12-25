package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final CustomerProfileService customerService;
    private final JwtUtil jwtUtil;

    public AuthController(CustomerProfileService customerService, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest req) {
        CustomerProfile c = new CustomerProfile();
        c.setEmail(req.getEmail());
        c.setCustomerId(req.getEmail()); // Using email as ID for simplicity if not provided
        c.setFullName(req.getFullName());
        c.setPhone(req.getPhone());
        // NOTE: In a real app, you would hash and store the password here.
        
        CustomerProfile created = customerService.createCustomer(c);
        return new ApiResponse(true, "User registered", created);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest req) {
        // Find user (mock logic used here as service doesn't store passwords)
        CustomerProfile c = customerService.findByCustomerId(req.getEmail())
             .or(() -> customerService.getAllCustomers().stream()
                     .filter(u -> u.getEmail().equals(req.getEmail())).findFirst())
             .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Check password (omitted for brevity, assume success)
        
        String token = jwtUtil.generateToken(c.getId(), c.getEmail(), "ROLE_ADMIN");
        return new ApiResponse(true, "Login success", token);
    }
}