package com.example.demo.repository;
import com.example.demo.model.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {
    List<PurchaseRecord> findByCustomerId(Long customerId);
}