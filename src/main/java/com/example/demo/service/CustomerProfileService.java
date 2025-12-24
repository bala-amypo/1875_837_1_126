package com.example.demo.service;
import com.example.demo.entity.CustomerProfile;
import java.util.List;

public interface CustomerProfileService {
    CustomerProfile createCustomer(CustomerProfile customer);
    CustomerProfile getCustomerById(Long id);
    CustomerProfile findByCustomerId(String customerId);
    CustomerProfile findByEmail(String email); // Added this
    List<CustomerProfile> getAllCustomers();
    CustomerProfile updateTier(Long id, String newTier);
    CustomerProfile updateStatus(Long id, boolean active);
}