package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.CustomerProfile;
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
        c.setCustomerId(req.getEmail());
        c.setFullName(req.getFullName());
        c.setPhone(req.getPhone());
        CustomerProfile created = customerService.createCustomer(c);
        return new ApiResponse(true, "User registered", created);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest req) {
        CustomerProfile c = customerService.findByCustomerId(req.getEmail())
             .or(() -> customerService.getAllCustomers().stream()
                     .filter(u -> u.getEmail().equals(req.getEmail())).findFirst())
             .orElseThrow(() -> new RuntimeException("User not found"));
        
        String token = jwtUtil.generateToken(c.getId(), c.getEmail(), "ROLE_ADMIN");
        return new ApiResponse(true, "Login success", token);
    }
}