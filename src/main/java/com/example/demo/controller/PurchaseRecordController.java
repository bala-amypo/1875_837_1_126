package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.CustomerProfile;
import com.example.demo.entity.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;
import com.example.demo.service.CustomerProfileService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRecordController {

    private final PurchaseRecordService purchaseService;
    private final CustomerProfileService customerService;

    public PurchaseRecordController(PurchaseRecordService purchaseService, CustomerProfileService customerService) {
        this.purchaseService = purchaseService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<PurchaseRecord> recordPurchase(@RequestBody PurchaseRecord purchase) {
        CustomerProfile customer = customerService.getCustomerById(purchase.getCustomer().getId());
        purchase.setCustomer(customer);
        return ResponseEntity.ok(purchaseService.recordPurchase(purchase));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PurchaseRecord>> getPurchasesByCustomer(@PathVariable Long customerId) {
        CustomerProfile customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(purchaseService.getPurchasesByCustomer(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseRecord> getPurchaseById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseRecord>> getAllPurchases() {
        return ResponseEntity.ok(purchaseService.getAllPurchases());
    }
}
