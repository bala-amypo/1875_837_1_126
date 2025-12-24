package com.example.demo.service.impl;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.service.CustomerProfileService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {
    private final CustomerProfileRepository repository;

    public CustomerProfileServiceImpl(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerProfile createCustomer(CustomerProfile customer) {
        if (repository.findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalArgumentException("Customer ID already exists");
        }
        if (customer.getCurrentTier() == null) customer.setCurrentTier("BRONZE");
        return repository.save(customer);
    }

    @Override
    public CustomerProfile getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
    
    // Implement other methods...
}