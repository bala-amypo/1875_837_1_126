package com.example.demo.controller;
import com.example.demo.dto.*;
import com.example.demo.model.CustomerProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.impl.CustomerProfileServiceImpl;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController @RequestMapping("/auth") @Tag(name="Authentication")
public class AuthController {
    private final CustomerProfileServiceImpl service;
    private final JwtUtil jwtUtil;
    public AuthController(CustomerProfileServiceImpl service, JwtUtil jwtUtil) { this.service = service; this.jwtUtil = jwtUtil; }
    
    @PostMapping("/register") public CustomerProfile register(@RequestBody RegisterRequest req) {
        CustomerProfile cp = new CustomerProfile(req.email, req.fullName, req.email, req.phone, "BRONZE", true, null);
        return service.createCustomer(cp);
    }
    @PostMapping("/login") public ApiResponse login(@RequestBody LoginRequest req) {
        CustomerProfile cp = service.findByEmailInternal(req.email);
        String token = jwtUtil.generateToken(cp.getId(), cp.getEmail(), "ROLE_ADMIN");
        return new ApiResponse(true, "Login success", token);
    }
}