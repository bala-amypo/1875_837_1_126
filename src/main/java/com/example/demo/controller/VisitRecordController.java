package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.VisitRecord;
import com.example.demo.service.VisitRecordService;
import com.example.demo.service.CustomerProfileService;

@RestController
@RequestMapping("/api/visits")
public class VisitRecordController {

    private final VisitRecordService visitService;
    private final CustomerProfileService customerService;

    public VisitRecordController(VisitRecordService visitService, CustomerProfileService customerService) {
        this.visitService = visitService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<VisitRecord> recordVisit(@RequestBody VisitRecord visit) {
        CustomerProfile customer = customerService.getCustomerById(visit.getCustomer().getId());
        visit.setCustomer(customer);
        return ResponseEntity.ok(visitService.recordVisit(visit));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<VisitRecord>> getVisitsByCustomer(@PathVariable Long customerId) {
        CustomerProfile customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(visitService.getVisitsByCustomer(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitRecord> getVisitById(@PathVariable Long id) {
        return ResponseEntity.ok(visitService.getVisitById(id));
    }

    @GetMapping
    public ResponseEntity<List<VisitRecord>> getAllVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }
}
