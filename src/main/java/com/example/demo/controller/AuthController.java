package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CustomerProfileService;
import com.example.demo.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {
    private final CustomerProfileService service;
    private final JwtUtil jwtUtil;

    public AuthController(CustomerProfileService service, JwtUtil jwtUtil) {
        this.service = service; this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequest request) {
        // Implementation calling service...
        return null;
    }
}