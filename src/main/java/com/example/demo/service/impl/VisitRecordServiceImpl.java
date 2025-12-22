package com.example.demo.service.impl;

import com.example.demo.model.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordRepository visitRecordRepository;

    // Constructor injection
    public VisitRecordServiceImpl(VisitRecordRepository visitRecordRepository) {
        this.visitRecordRepository = visitRecordRepository;
    }

    // 1. Record a visit
    @Override
    public VisitRecord recordVisit(VisitRecord visit) {
        // Channel validation
        String channel = visit.getChannel();
        if (!"STORE".equalsIgnoreCase(channel)
                && !"APP".equalsIgnoreCase(channel)
                && !"WEB".equalsIgnoreCase(channel)) {
            throw new IllegalArgumentException("Invalid channel");
        }
        // Save visit record
        return visitRecordRepository.save(visit);
    }

    // 2. Get visits by customer ID
    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return visitRecordRepository.findByCustomerId(customerId);
    }

    // 3. Get all visits
    @Override
    public List<VisitRecord> getAllVisits() {
        return visitRecordRepository.findAll();
    }

    // 4. Get visit by ID
    @Override
    public VisitRecord getVisitById(Long id) {
        return visitRecordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }
}
