package com.example.demo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController @RequestMapping("/api/customers") @Tag(name="Customer Profiles")
public class CustomerProfileController {
    private final CustomerProfileService service;
    public CustomerProfileController(CustomerProfileService service) { this.service = service; }
    
    @PostMapping public CustomerProfile create(@RequestBody CustomerProfile c) { return service.createCustomer(c); }
    
    @GetMapping("/{id}") public CustomerProfile getById(@PathVariable Long id) { 
        return service.getCustomerById(id).orElseThrow(() -> new NoSuchElementException("Customer not found")); 
    }
    
    @GetMapping public List<CustomerProfile> getAll() { return service.getAllCustomers(); }
    
    @PutMapping("/{id}/tier") public CustomerProfile updateTier(@PathVariable Long id, @RequestParam String newTier) { 
        return service.updateTier(id, newTier); 
    }
    
    @GetMapping("/lookup/{customerId}") public CustomerProfile lookup(@PathVariable String customerId) { 
        return service.findByCustomerId(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found")); 
    }
}