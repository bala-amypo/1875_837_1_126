package com.example.demo.service;

import com.example.demo.model.PurchaseRecord;
import com.example.demo.repository.PurchaseRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

    private final PurchaseRecordRepository purchaseRecordRepository;

    // Constructor injection 
    public PurchaseRecordServiceImpl(PurchaseRecordRepository purchaseRecordRepository) {
        this.purchaseRecordRepository = purchaseRecordRepository;
    }

    // Record a purchase
    @Override
    public PurchaseRecord recordPurchase(PurchaseRecord purchase) {

        // Validation rule
        if (purchase.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Save purchase
        return purchaseRecordRepository.save(purchase);
    }

    // Get purchases by customer ID
    @Override
    public List<PurchaseRecord> getPurchasesByCustomer(Long customerId) {
        return purchaseRecordRepository.findByCustomerId(customerId);
    }

    // Get all purchases
    @Override
    public List<PurchaseRecord> getAllPurchases() {
        return purchaseRecordRepository.findAll();
    }

    // Get purchase by ID
    @Override
    public PurchaseRecord getPurchaseById(Long id) {
        return purchaseRecordRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Purchase record not found"));
    }
}
