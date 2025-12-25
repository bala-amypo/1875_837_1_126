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

    private final PurchaseRecordService service;

    public PurchaseRecordController(PurchaseRecordService service) {
        this.service = service;
    }

    @PostMapping
    public PurchaseRecord create(@RequestBody PurchaseRecord p) {
        return service.recordPurchase(p);
    }

    @GetMapping("/customer/{customerId}")
    public List<PurchaseRecord> getByCustomer(@PathVariable Long customerId) {
        return service.getPurchasesByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public PurchaseRecord getById(@PathVariable Long id) {
        return service.getPurchaseById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("Purchase not found"));
    }

    @GetMapping
    public List<PurchaseRecord> getAll() {
        return service.getAllPurchases();
    }
}