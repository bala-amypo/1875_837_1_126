package com.example.demo.service;
import com.example.demo.entity.VisitRecord;
import java.util.List;

public interface VisitRecordService {
    VisitRecord recordVisit(VisitRecord visit);
    List<VisitRecord> getVisitsByCustomer(Long customerId); // Fixed to Long
    VisitRecord getVisitById(Long id);
    List<VisitRecord> getAllVisits();
}