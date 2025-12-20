package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomerProfileService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomerProfileService customerService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerProfileService customerService,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerProfile> register(@RequestBody RegisterRequest request) {

        CustomerProfile customer = new CustomerProfile();
        customer.setCustomerId(request.getEmail());
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setPhone(request.getPhone());

        CustomerProfile saved = customerService.createCustomer(customer);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        CustomerProfile customer =
                customerService.findByCustomerId(request.getEmail());

        String token = jwtUtil.generateToken(
                customer.getId(),
                customer.getEmail(),
                "USER"
        );

        return ResponseEntity.ok(token);
    }
}
