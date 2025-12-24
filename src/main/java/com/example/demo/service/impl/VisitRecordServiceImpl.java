package com.example.demo.service.impl;

import com.example.demo.entity.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {
    private final VisitRecordRepository repository;

    public VisitRecordServiceImpl(VisitRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {
        List<String> validChannels = Arrays.asList("STORE", "APP", "WEB");
        if (visit.getChannel() == null || !validChannels.contains(visit.getChannel().toUpperCase())) {
            throw new IllegalArgumentException("Invalid channel");
        }
        return repository.save(visit);
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return repository.findAll();
    }

    @Override
    public VisitRecord getVisitById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }
}