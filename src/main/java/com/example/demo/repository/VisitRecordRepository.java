package com.example.demo.repository;

import com.example.demo.entity.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {
    
    // Extra method required: find by customer database ID (Long)
    List<VisitRecord> findByCustomerId(Long customerId);
    
    // Extra method required: find visits within a date range
    List<VisitRecord> findByVisitDateBetween(LocalDate start, LocalDate end);
}