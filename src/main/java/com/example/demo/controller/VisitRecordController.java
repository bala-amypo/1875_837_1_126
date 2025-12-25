package com.example.demo.controller;

import com.example.demo.entity.VisitRecord;
import com.example.demo.service.VisitRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visit Records")
public class VisitRecordController {

    private final VisitRecordService service;

    public VisitRecordController(VisitRecordService service) {
        this.service = service;
    }

    @PostMapping
    public VisitRecord create(@RequestBody VisitRecord visit) {
        return service.recordVisit(visit);
    }

    @GetMapping("/customer/{customerId}")
    public List<VisitRecord> getByCustomer(@PathVariable Long customerId) {
        return service.getVisitsByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public VisitRecord getById(@PathVariable Long id) {
        return service.getVisitById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("Visit record not found"));
    }

    @GetMapping
    public List<VisitRecord> getAll() {
        return service.getAllVisits();
    }
}