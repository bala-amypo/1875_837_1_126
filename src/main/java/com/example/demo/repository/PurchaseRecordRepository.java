package com.example.demo.repository;

import com.example.demo.model.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {

    // Find all purchases made by a specific customer
    List<PurchaseRecord> findByCustomerId(Long customerId);

    // Find all purchases between two dates
    List<PurchaseRecord> findByPurchaseDateBetween(LocalDate start, LocalDate end);
}
