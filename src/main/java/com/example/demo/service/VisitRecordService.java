package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.VisitRecord;

public interface VisitRecordService {

    VisitRecord recordVisit(VisitRecord visit);

    List<VisitRecord> getVisitsByCustomer(CustomerProfile customer);

    List<VisitRecord> getAllVisits();

    VisitRecord getVisitById(Long id);
}
