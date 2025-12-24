package com.example.demo.service;

import java.util.List;

import com.example.demo.CustomerProfile;

public interface CustomerProfileService {

    CustomerProfile createCustomer(CustomerProfile customer);

    CustomerProfile getCustomerById(Long id);

    CustomerProfile findByEmail(String email);

    List<CustomerProfile> getAllCustomers();

    CustomerProfile updateTier(Long id, String newTier);

    CustomerProfile updateStatus(Long id, boolean active);
}
