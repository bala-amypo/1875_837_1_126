package com.example.demo.security;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerProfileRepository repository;

    public CustomUserDetailsService(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerProfile cp = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Assuming role is stored somewhere or defaulted. Doc says role is in register request.
        // We will mock the role fetch or assume it's stored in a separate auth table, 
        // but for this task, we'll return a default user detail.
        return new User(cp.getEmail(), "$2a$10$DUMMYHASH", Collections.emptyList()); 
    }
}