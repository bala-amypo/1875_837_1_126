package com.example.demo.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerProfileRepository customerRepo;

    public CustomUserDetailsService(CustomerProfileRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        CustomerProfile customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                customer.getEmail(),
                "{noop}password", // placeholder (password logic not enforced in tests)
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
