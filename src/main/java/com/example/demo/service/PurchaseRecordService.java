package com.example.demo.service;
import com.example.demo.entity.PurchaseRecord;
import java.util.List;

public interface PurchaseRecordService {
    PurchaseRecord recordPurchase(PurchaseRecord purchase);
    List<PurchaseRecord> getPurchasesByCustomer(Long customerId); // Fixed to Long
    PurchaseRecord getPurchaseById(Long id);
    List<PurchaseRecord> getAllPurchases();
}