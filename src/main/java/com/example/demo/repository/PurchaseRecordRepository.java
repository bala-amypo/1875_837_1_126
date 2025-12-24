package com.example.demo.repository;

import com.example.demo.entity.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    
    // Extra method required: find by customer database ID (Long)
    List<PurchaseRecord> findByCustomerId(Long customerId);
    
    // Extra method required: find purchases within a date range
    List<PurchaseRecord> findByPurchaseDateBetween(LocalDate start, LocalDate end);
}