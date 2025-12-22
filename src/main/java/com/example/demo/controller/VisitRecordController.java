package com.example.demo.controller;

import com.example.demo.model.VisitRecord;
import com.example.demo.service.VisitRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitRecordController {

    private final VisitRecordService visitRecordService;

    // Constructor injection
    public VisitRecordController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    //  POST
    @PostMapping
    public ResponseEntity<VisitRecord> recordVisit(@RequestBody VisitRecord visit) {
        VisitRecord savedVisit = visitRecordService.recordVisit(visit);
        return ResponseEntity.ok(savedVisit);
    }

    //  GET 
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<VisitRecord>> getVisitsByCustomer(@PathVariable Long customerId) {
        List<VisitRecord> visits = visitRecordService.getVisitsByCustomer(customerId);
        return ResponseEntity.ok(visits);
    }

    //  GET /api/visits/{id}
    @GetMapping("/{id}")
    public ResponseEntity<VisitRecord> getVisitById(@PathVariable Long id) {
        VisitRecord visit = visitRecordService.getVisitById(id);
        return ResponseEntity.ok(visit);
    }

    // GET /api/visits
    @GetMapping
    public ResponseEntity<List<VisitRecord>> getAllVisits() {
        List<VisitRecord> visits = visitRecordService.getAllVisits();
        return ResponseEntity.ok(visits);
    }
}
