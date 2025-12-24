package com.example.demo.repository;

import com.example.demo.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    
    // Extra method required: find by String customerId
    Optional<CustomerProfile> findByCustomerId(String customerId);
    
    // Extra method required: find by email
    Optional<CustomerProfile> findByEmail(String email);
}