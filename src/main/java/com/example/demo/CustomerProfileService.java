package com.example.demo;
import java.util.List;
import java.util.Optional;

public interface CustomerProfileService {
    CustomerProfile createCustomer(CustomerProfile customer);
    Optional<CustomerProfile> getCustomerById(Long id);
    Optional<CustomerProfile> findByCustomerId(String customerId);
    Optional<CustomerProfile> findByEmail(String email);
    List<CustomerProfile> getAllCustomers();
    CustomerProfile updateTier(Long id, String newTier);
    CustomerProfile updateStatus(Long id, boolean active);
}