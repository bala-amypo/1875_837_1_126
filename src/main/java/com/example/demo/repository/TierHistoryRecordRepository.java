package com.example.demo.repository;

import com.example.demo.model.TierHistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TierHistoryRecordRepository
        extends JpaRepository<TierHistoryRecord, Long> {

    List<TierHistoryRecord> findByCustomerId(Long customerId);
}
