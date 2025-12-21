package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.VisitRecord;

public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {

    List<VisitRecord> findByCustomerId(Long customerId);

    List<VisitRecord> findByVisitDateBetween(LocalDate start, LocalDate end);
}
