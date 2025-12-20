package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.service.CustomerProfileService;

@RestController
@RequestMapping("/api/customers")
public class CustomerProfileController {

    private final CustomerProfileService customerService;

    public CustomerProfileController(CustomerProfileService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerProfile create(@RequestBody CustomerProfile customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public CustomerProfile getById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerProfile> getAll() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{id}/tier")
    public CustomerProfile updateTier(@PathVariable Long id,
                                      @RequestParam String newTier) {
        return customerService.updateTier(id, newTier);
    }

    @GetMapping("/lookup/{customerId}")
    public CustomerProfile findByCustomerId(@PathVariable String customerId) {
        return customerService.findByCustomerId(customerId);
    }
}
