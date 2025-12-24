package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.PurchaseRecord;

public interface PurchaseRecordService {

    PurchaseRecord recordPurchase(PurchaseRecord purchase);

    List<PurchaseRecord> getPurchasesByCustomer(CustomerProfile customer);

    List<PurchaseRecord> getAllPurchases();

    PurchaseRecord getPurchaseById(Long id);
}
