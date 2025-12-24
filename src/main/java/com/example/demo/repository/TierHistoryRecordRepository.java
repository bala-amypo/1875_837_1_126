package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.TierHistoryRecord;

public interface TierHistoryRecordRepository extends JpaRepository<TierHistoryRecord, Long> {

    List<TierHistoryRecord> findByCustomer(CustomerProfile customer);
}
