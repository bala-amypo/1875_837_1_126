package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRecordController {

    private final PurchaseRecordService purchaseService;

    public PurchaseRecordController(PurchaseRecordService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public PurchaseRecord create(@RequestBody PurchaseRecord purchase) {
        return purchaseService.recordPurchase(purchase);
    }

    @GetMapping("/customer/{customerId}")
    public List<PurchaseRecord> getByCustomer(@PathVariable Long customerId) {
        return purchaseService.getPurchasesByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public PurchaseRecord getById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    @GetMapping
    public List<PurchaseRecord> getAll() {
        return purchaseService.getAllPurchases();
    }
}
