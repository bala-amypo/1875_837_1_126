package com.example.demo.service;
import com.example.demo.entity.*;
import java.util.List;

public interface CustomerProfileService {
    CustomerProfile createCustomer(CustomerProfile customer);
    CustomerProfile getCustomerById(Long id);
    CustomerProfile findByCustomerId(String customerId);
    List<CustomerProfile> getAllCustomers();
    CustomerProfile updateTier(Long id, String newTier);
}

// ... other interfaces similar pattern