package com.example.demo;
import java.util.List;

public interface CustomerProfileService {
    CustomerProfile createCustomer(CustomerProfile customer);
    CustomerProfile getCustomerById(Long id);
    CustomerProfile findByCustomerId(String customerId);
    List<CustomerProfile> getAllCustomers();
    CustomerProfile updateTier(Long id, String newTier);
    CustomerProfile updateStatus(Long id, boolean active);
}