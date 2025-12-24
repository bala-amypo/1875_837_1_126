package com.example.demo.controller;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.service.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Profiles")
public class CustomerProfileController {
    private final CustomerProfileService service;

    public CustomerProfileController(CustomerProfileService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create customer")
    public CustomerProfile create(@RequestBody CustomerProfile customer) {
        return service.createCustomer(customer);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID")
    public CustomerProfile getById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @GetMapping
    @Operation(summary = "List all customers")
    public List<CustomerProfile> getAll() {
        return service.getAllCustomers();
    }

    @PutMapping("/{id}/tier")
    @Operation(summary = "Update tier")
    public CustomerProfile updateTier(@PathVariable Long id, @RequestParam String newTier) {
        return service.updateTier(id, newTier);
    }

    @GetMapping("/lookup/{customerId}")
    @Operation(summary = "Lookup by Customer ID String")
    public CustomerProfile lookup(@PathVariable String customerId) {
        return service.findByCustomerId(customerId);
    }
}