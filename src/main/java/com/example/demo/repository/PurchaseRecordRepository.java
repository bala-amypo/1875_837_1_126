package com.example.demo.repository;

import com.example.demo.entity.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    // Required method: Find all purchases for a specific customer (Long database ID)
    List<PurchaseRecord> findByCustomerId(Long customerId);

    // Required method: Find purchases within a date range
    List<PurchaseRecord> findByPurchaseDateBetween(LocalDate start, LocalDate end);
}