package com.example.demo.service.impl;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.service.CustomerProfileService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository repository;

    // Constructor Injection
    public CustomerProfileServiceImpl(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerProfile createCustomer(CustomerProfile customer) {
        if (repository.findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalArgumentException("Customer ID already exists");
        }
        if (repository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (repository.findByPhone(customer.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone already exists");
        }
        return repository.save(customer);
    }

    @Override
    public CustomerProfile getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    @Override
    public Optional<CustomerProfile> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<CustomerProfile> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public CustomerProfile updateTier(Long id, String newTier) {
        CustomerProfile customer = getCustomerById(id);
        customer.setCurrentTier(newTier);
        return repository.save(customer);
    }

    @Override
    public CustomerProfile updateStatus(Long id, boolean active) {
        CustomerProfile customer = getCustomerById(id);
        customer.setActive(active);
        return repository.save(customer);
    }
}