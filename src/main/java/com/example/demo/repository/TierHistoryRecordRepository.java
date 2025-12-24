package com.example.demo.repository;

import com.example.demo.entity.TierHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TierHistoryRecordRepository extends JpaRepository<TierHistoryRecord, Long> {
    // Required method: Get full tier history for a specific customer
    List<TierHistoryRecord> findByCustomerId(Long customerId);

    // Required method: Find history records within a timestamp range
    List<TierHistoryRecord> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);
}