package com.example.demo;
import java.util.List;

public interface PurchaseRecordService {
    PurchaseRecord recordPurchase(PurchaseRecord purchase);
    List<PurchaseRecord> getPurchasesByCustomer(Long customerId);
    PurchaseRecord getPurchaseById(Long id);
    List<PurchaseRecord> getAllPurchases();
}