package com.example.demo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.VisitRecord;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.VisitRecordService;

@Service
@Transactional
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordRepository visitRepo;
    private final Set<String> validChannels = Set.of("STORE", "APP", "WEB");

    public VisitRecordServiceImpl(VisitRecordRepository visitRepo) {
        this.visitRepo = visitRepo;
    }

    @Override
    public VisitRecord recordVisit(VisitRecord visit) {
        if (!validChannels.contains(visit.getChannel())) {
            throw new IllegalArgumentException("Invalid channel");
        }
        return visitRepo.save(visit);
    }

    @Override
    public List<VisitRecord> getVisitsByCustomer(CustomerProfile customer) {
        return visitRepo.findByCustomer(customer);
    }

    @Override
    public List<VisitRecord> getAllVisits() {
        return visitRepo.findAll();
    }

    @Override
    public VisitRecord getVisitById(Long id) {
        return visitRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Visit record not found"));
    }
}
