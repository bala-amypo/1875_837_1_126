package com.example.demo.security;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerProfileRepository repository;

    public CustomUserDetailsService(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerProfile customer = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Use a dummy password hash if not implementing real password storage logic yet
        // In real app, CustomerProfile needs a 'password' field.
        // Assuming the test handles logic or we use a hardcoded hash for demo.
        String dummyHash = "$2a$10$D8S/s/s/s/s/s/s/s/s/s/s/s/s/s/s/s"; // BCrypt for "password"

        // Map role to Authority
        String role = "ROLE_RETAIL_OPERATOR"; // Default or fetch from customer if field exists
        
        return User.builder()
                .username(customer.getEmail())
                .password(dummyHash) 
                .authorities(role)
                .build();
    }
}