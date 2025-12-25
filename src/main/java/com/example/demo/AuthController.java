package com.example.demo;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController @RequestMapping("/auth") @Tag(name="Authentication")
public class AuthController {
    private final CustomerProfileService service;
    private final JwtUtil jwtUtil;
    public AuthController(CustomerProfileService service, JwtUtil jwtUtil) { this.service = service; this.jwtUtil = jwtUtil; }
    
    @PostMapping("/register") public CustomerProfile register(@RequestBody RegisterRequest req) {
        CustomerProfile cp = new CustomerProfile(req.email, req.fullName, req.email, req.phone, "BRONZE", true, null);
        return service.createCustomer(cp);
    }
    
    @PostMapping("/login") public ApiResponse login(@RequestBody LoginRequest req) {
        // FIXED: Service returns Entity directly now
        CustomerProfile cp = service.findByEmail(req.email);
        String token = jwtUtil.generateToken(cp.getId(), cp.getEmail(), "ROLE_ADMIN");
        return new ApiResponse(true, "Login success", token);
    }
}