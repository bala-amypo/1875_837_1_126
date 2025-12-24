package com.example.demo.security;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerProfileRepository customerProfileRepository;

    // Constructor Injection as required
    public CustomUserDetailsService(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerProfile customer = customerProfileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Roles from helper doc: ADMIN, CRM_MANAGER, RETAIL_OPERATOR
        // We add ROLE_ prefix for standard Spring Security hasRole() checks
        String roleName = customer.getRole().startsWith("ROLE_") ? 
                          customer.getRole() : "ROLE_" + customer.getRole();

        return new User(
                customer.getEmail(),
                customer.getPassword(), // This should be the hashed password
                Collections.singletonList(new SimpleGrantedAuthority(roleName))
        );
    }
}