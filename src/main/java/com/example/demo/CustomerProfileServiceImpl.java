package com.example.demo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {
    private final CustomerProfileRepository repository;
    public CustomerProfileServiceImpl(CustomerProfileRepository repository) { this.repository = repository; }

    public CustomerProfile createCustomer(CustomerProfile customer) {
        if (repository.findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalArgumentException("Customer ID already exists");
        }
        return repository.save(customer);
    }
    // Returning Optional is REQUIRED by Test Suite
    public Optional<CustomerProfile> getCustomerById(Long id) { return repository.findById(id); }
    public Optional<CustomerProfile> findByCustomerId(String customerId) { return repository.findByCustomerId(customerId); }
    public Optional<CustomerProfile> findByEmail(String email) { return repository.findByEmail(email); }
    
    public List<CustomerProfile> getAllCustomers() { return repository.findAll(); }
    
    public CustomerProfile updateTier(Long id, String newTier) {
        CustomerProfile cp = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found"));
        cp.setCurrentTier(newTier);
        return repository.save(cp);
    }
    public CustomerProfile updateStatus(Long id, boolean active) {
        CustomerProfile cp = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found"));
        cp.setActive(active);
        return repository.save(cp);
    }
}