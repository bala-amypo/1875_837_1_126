package com.example.demo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.entity.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private static final Set<String> VALID_CHANNELS = Set.of("STORE", "APP", "WEB");

    private final VisitRecordRepository visitRecordRepository;

    public VisitRecordServiceImpl(VisitRecordRepository visitRecordRepository) {
        this.visitRecordRepository = visitRecordRepository;
    }

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {

        if (!VALID_CHANNELS.contains(visit.getChannel())) {
            throw new IllegalArgumentException("Invalid channel");
        }

        return visitRecordRepository.save(visit);
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(Long customerId) {
        return visitRecordRepository.findByCustomerId(customerId);
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return visitRecordRepository.findAll();
    }

    @Override
    public VisitRecord getVisitById(Long id) {
        return visitRecordRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }
}
