package com.example.demo;
import org.springframework.stereotype.Service;
import java.util.List;
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
    public CustomerProfile getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
    public CustomerProfile findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }
    public List<CustomerProfile> getAllCustomers() { return repository.findAll(); }
    public CustomerProfile updateTier(Long id, String newTier) {
        CustomerProfile cp = getCustomerById(id);
        cp.setCurrentTier(newTier);
        return repository.save(cp);
    }
    public CustomerProfile updateStatus(Long id, boolean active) {
        CustomerProfile cp = getCustomerById(id);
        cp.setActive(active);
        return repository.save(cp);
    }
    // Helper used by AuthController only
    public CustomerProfile findByEmailInternal(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}