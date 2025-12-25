package com.example.demo;
import java.util.List;
import java.util.Optional;

public interface VisitRecordService {
    VisitRecord recordVisit(VisitRecord visit);
    List<VisitRecord> getVisitsByCustomer(Long customerId);
    Optional<VisitRecord> getVisitById(Long id);
    List<VisitRecord> getAllVisits();
}