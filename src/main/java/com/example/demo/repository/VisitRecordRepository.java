package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.VisitRecord;

public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {

    List<VisitRecord> findByCustomer(CustomerProfile customer);
}
