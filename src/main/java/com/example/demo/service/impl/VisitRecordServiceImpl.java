package com.example.demo.service.impl;
import com.example.demo.model.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {
    private final VisitRecordRepository repository;
    public VisitRecordServiceImpl(VisitRecordRepository repository) { this.repository = repository; }
    
    public VisitRecord recordVisit(VisitRecord visit) {
        if (!Set.of("STORE", "APP", "WEB").contains(visit.getChannel())) throw new IllegalArgumentException("Invalid channel");
        return repository.save(visit);
    }
    public List<VisitRecord> getVisitsByCustomer(Long customerId) { return repository.findByCustomerId(customerId); }
    public VisitRecord getVisitById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }
    public List<VisitRecord> getAllVisits() { return repository.findAll(); }
}