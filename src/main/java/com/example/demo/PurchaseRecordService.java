package com.example.demo;
import java.util.List;
import java.util.Optional;

public interface PurchaseRecordService {
    PurchaseRecord recordPurchase(PurchaseRecord purchase);
    List<PurchaseRecord> getPurchasesByCustomer(Long customerId);
    Optional<PurchaseRecord> getPurchaseById(Long id);
    List<PurchaseRecord> getAllPurchases();
}