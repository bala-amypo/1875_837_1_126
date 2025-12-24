package com.example.demo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.PurchaseRecord;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.service.PurchaseRecordService;

@Service
@Transactional
public class PurchaseRecordServiceImpl implements PurchaseRecordService {

    private final PurchaseRecordRepository purchaseRepo;

    public PurchaseRecordServiceImpl(PurchaseRecordRepository purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
    }

    @Override
    public PurchaseRecord recordPurchase(PurchaseRecord purchase) {
        if (purchase.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return purchaseRepo.save(purchase);
    }

    @Override
    public List<PurchaseRecord> getPurchasesByCustomer(CustomerProfile customer) {
        return purchaseRepo.findByCustomer(customer);
    }

    @Override
    public List<PurchaseRecord> getAllPurchases() {
        return purchaseRepo.findAll();
    }

    @Override
    public PurchaseRecord getPurchaseById(Long id) {
        return purchaseRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Purchase record not found"));
    }
}
