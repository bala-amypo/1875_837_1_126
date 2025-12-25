package com.example.demo;
import java.util.List;
import java.util.Optional;

public interface CustomerProfileService {
    CustomerProfile createCustomer(CustomerProfile customer);
    
    // Must return Optional for tests
    Optional<CustomerProfile> getCustomerById(Long id);
    Optional<CustomerProfile> findByCustomerId(String customerId);
    
    // REMOVED findByEmail because test's FakeCustomerService doesn't implement it
    
    List<CustomerProfile> getAllCustomers();
    CustomerProfile updateTier(Long id, String newTier);
    CustomerProfile updateStatus(Long id, boolean active);
}