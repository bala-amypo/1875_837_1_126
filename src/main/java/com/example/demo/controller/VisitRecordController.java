package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.VisitRecord;
import com.example.demo.service.VisitRecordService;

@RestController
@RequestMapping("/api/visits")
public class VisitRecordController {

    private final VisitRecordService visitService;

    public VisitRecordController(VisitRecordService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public VisitRecord create(@RequestBody VisitRecord visit) {
        return visitService.recordVisit(visit);
    }

    @GetMapping("/customer/{customerId}")
    public List<VisitRecord> getByCustomer(@PathVariable Long customerId) {
        return visitService.getVisitsByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public VisitRecord getById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @GetMapping
    public List<VisitRecord> getAll() {
        return visitService.getAllVisits();
    }
}
