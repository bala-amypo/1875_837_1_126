package com.example.demo.repository;

import com.example.demo.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {
    // Required method: Find all visits for a specific customer (Long database ID)
    List<VisitRecord> findByCustomerId(Long customerId);

    // Required method: Find visits within a date range
    List<VisitRecord> findByVisitDateBetween(LocalDate start, LocalDate end);
}