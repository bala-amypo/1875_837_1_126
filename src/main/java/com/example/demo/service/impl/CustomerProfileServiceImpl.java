package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.CustomerProfile;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.service.CustomerProfileService;

@Service
@Transactional
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository customerRepo;

    public CustomerProfileServiceImpl(CustomerProfileRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerProfile createCustomer(CustomerProfile customer) {
        customerRepo.findByEmail(customer.getEmail()).ifPresent(c -> {
            throw new IllegalArgumentException("Email already exists");
        });
        if (customer.getCurrentTier() == null) {
            customer.setCurrentTier("BRONZE");
        }
        if (customer.getCreatedAt() == null) {
            customer.setCreatedAt(LocalDateTime.now());
        }
        return customerRepo.save(customer);
    }

    @Override
    public CustomerProfile getCustomerById(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    @Override
    public CustomerProfile findByEmail(String email) {
        return customerRepo.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    @Override
    public List<CustomerProfile> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public CustomerProfile updateTier(Long id, String newTier) {
        CustomerProfile customer = getCustomerById(id);
        customer.setCurrentTier(newTier);
        return customerRepo.save(customer);
    }

    @Override
    public CustomerProfile updateStatus(Long id, boolean active) {
        CustomerProfile customer = getCustomerById(id);
        customer.setActive(active);
        return customerRepo.save(customer);
    }
}
