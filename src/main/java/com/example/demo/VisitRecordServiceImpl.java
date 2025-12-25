package com.example.demo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
    public Optional<VisitRecord> getVisitById(Long id) { return repository.findById(id); }
    public List<VisitRecord> getAllVisits() { return repository.findAll(); }
}