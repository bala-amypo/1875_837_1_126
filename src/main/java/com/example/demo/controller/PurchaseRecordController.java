package com.example.demo.controller;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRecordController {

    private final PurchaseRecordService purchaseRecordService;

    // Constructor injection
    public PurchaseRecordController(PurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

   
    @PostMapping
    public ResponseEntity<PurchaseRecord> recordPurchase(
            @RequestBody PurchaseRecord purchase) {

        PurchaseRecord savedPurchase =
                purchaseRecordService.recordPurchase(purchase);

        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }

   
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PurchaseRecord>> getPurchasesByCustomer(
            @PathVariable Long customerId) {

        List<PurchaseRecord> purchases =
                purchaseRecordService.getPurchasesByCustomer(customerId);

        return ResponseEntity.ok(purchases);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseRecord> getPurchaseById(
            @PathVariable Long id) {

        PurchaseRecord purchase =
                purchaseRecordService.getPurchaseById(id);

        return ResponseEntity.ok(purchase);
    }

    
    @GetMapping
    public ResponseEntity<List<PurchaseRecord>> getAllPurchases() {

        List<PurchaseRecord> purchases =
                purchaseRecordService.getAllPurchases();

        return ResponseEntity.ok(purchases);
    }
}
