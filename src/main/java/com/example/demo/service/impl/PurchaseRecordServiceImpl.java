package com.example.demo.service.impl;

import com.example.demo.entity.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordRepository repository;
    private static final Set<String> VALID_CHANNELS = Set.of("STORE", "APP", "WEB");

    public VisitRecordServiceImpl(VisitRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {
        if (visit.getChannel() == null || !VALID_CHANNELS.contains(visit.getChannel())) {
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
    public Optional<VisitRecord> getVisitById(Long id) {
        return repository.findById(id);
    }
}