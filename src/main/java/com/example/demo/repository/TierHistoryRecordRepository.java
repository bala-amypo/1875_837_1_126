package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TierHistoryRecord;

public interface TierHistoryRecordRepository extends JpaRepository<TierHistoryRecord, Long> {

    List<TierHistoryRecord> findByCustomerId(Long customerId);

    List<TierHistoryRecord> findByChangedAtBetween(LocalDateTime start, LocalDateTime end);
}
