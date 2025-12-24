package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.PurchaseRecord;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {

    List<PurchaseRecord> findByCustomer(CustomerProfile customer);
}
