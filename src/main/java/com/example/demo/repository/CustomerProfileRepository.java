package com.example.demo.repository;

import com.example.demo.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    // Required method: Find by the unique String customerId
    Optional<CustomerProfile> findByCustomerId(String customerId);

    // Required method: Find by the unique email address
    Optional<CustomerProfile> findByEmail(String email);
}