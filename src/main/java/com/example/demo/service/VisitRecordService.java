package com.example.demo.service;
import com.example.demo.model.VisitRecord;
import java.util.List;
public interface VisitRecordService {
    VisitRecord recordVisit(VisitRecord visit);
    List<VisitRecord> getVisitsByCustomer(Long customerId);
    VisitRecord getVisitById(Long id);
    List<VisitRecord> getAllVisits();
}