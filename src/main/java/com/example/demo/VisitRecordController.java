package com.example.demo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController @RequestMapping("/api/visits") @Tag(name="Visit Records")
public class VisitRecordController {
    private final VisitRecordService service;
    public VisitRecordController(VisitRecordService service) { this.service = service; }
    @PostMapping public VisitRecord record(@RequestBody VisitRecord v) { return service.recordVisit(v); }
    @GetMapping("/customer/{customerId}") public List<VisitRecord> getByCustomer(@PathVariable Long customerId) { return service.getVisitsByCustomer(customerId); }
    // Unwrap Optional
    @GetMapping("/{id}") public VisitRecord getById(@PathVariable Long id) { 
        return service.getVisitById(id).orElseThrow(() -> new NoSuchElementException("Visit record not found")); 
    }
    @GetMapping public List<VisitRecord> getAll() { return service.getAllVisits(); }
}