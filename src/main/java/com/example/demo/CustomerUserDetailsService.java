package com.example.demo;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final CustomerProfileRepository repository;
    public CustomUserDetailsService(CustomerProfileRepository repository) { this.repository = repository; }
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerProfile cp = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(cp.getEmail(), "$2a$10$DUMMYHASH", Collections.emptyList());
    }
}