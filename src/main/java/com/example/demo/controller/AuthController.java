package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.CustomerProfile;
import com.example.demo.service.CustomerProfileService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomerProfileService customerService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerProfileService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerProfile> register(@RequestBody CustomerProfile customer) {
        // Encode password before saving
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        CustomerProfile created = customerService.createCustomer(customer);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerProfile loginRequest) {
        CustomerProfile customer = customerService.findByEmail(loginRequest.getEmail());
        if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        // Normally JWT would be returned, but for CRUD testing, simple message is fine
        return ResponseEntity.ok("Login successful");
    }
}
