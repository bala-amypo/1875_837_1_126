package com.example.demo.repository;

import com.example.demo.model.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {

    // Find all visits made by a specific customer
    List<VisitRecord> findByCustomerId(Long customerId);

    // Find visits between two dates
    List<VisitRecord> findByVisitDateBetween(LocalDate start, LocalDate end);
}
