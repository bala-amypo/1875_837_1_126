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
    private final VisitRecordService visitRecordService;

    public VisitRecordController(VisitRecordService visitRecordService) {
        this.visitRecordService = visitRecordService;
    }

    @PostMapping
    public VisitRecord recordVisit(@RequestBody VisitRecord visit) {
        return visitRecordService.recordVisit(visit);
    }

    @GetMapping("/customer/{customerId}")
    public List<VisitRecord> getVisitsByCustomer(@PathVariable Long customerId) {
        return visitRecordService.getVisitsByCustomer(customerId);
    }

    @GetMapping("/{id}")
    public VisitRecord getVisitById(@PathVariable Long id) {
        return visitRecordService.getVisitById(id);
    }

    @GetMapping
    public List<VisitRecord> getAllVisits() {
        return visitRecordService.getAllVisits();
    }
}