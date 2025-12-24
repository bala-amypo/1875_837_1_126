package com.example.demo.repository;

import com.example.demo.entity.TierHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TierHistoryRecordRepository extends JpaRepository<TierHistoryRecord, Long> {
    
    // Extra method required: find audit history by customer database ID (Long)
    List<TierHistoryRecord> findByCustomerId(Long customerId);
    
    // Extra method required: find audit records within a timestamp range
    List<TierHistoryRecord> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);
}