package com.example.demo.controller;

import com.example.demo.entity.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@Tag(name = "Purchase Records")
public class PurchaseRecordController {
    private final PurchaseRecordService purchaseRecordService;

    public PurchaseRecordController(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    @PostMapping
    public PurchaseRecord recordPurchase(@RequestBody PurchaseRecord purchase) {
        // The entity uses 'Long customerId', so we pass the object directly.
        // Validation for positive amount is handled in service.
        return purchaseRecordService.recordPurchase(purchase);
    }

    @GetMapping("/customer/{customerId}")
    public List<PurchaseRecord> getPurchasesByCustomer(@PathVariable Long customerId) {
        return purchaseRecordService.getPurchasesByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public PurchaseRecord getPurchaseById(@PathVariable Long id) {
        return purchaseRecordService.getPurchaseById(id);
    }

    @GetMapping
    public List<PurchaseRecord> getAllPurchases() {
        return purchaseRecordService.getAllPurchases();
    }
}