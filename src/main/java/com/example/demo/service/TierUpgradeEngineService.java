package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TierHistoryRecord;

public interface TierUpgradeEngineService {

    // Evaluates and upgrades the customer's tier if criteria met
    TierHistoryRecord evaluateAndUpgradeTier(Long customerId);

    // Returns all tier history records for a specific customer
    List<TierHistoryRecord> getHistoryByCustomer(Long customerId);

    // Returns all tier history records in the system
    List<TierHistoryRecord> getAllHistory();
}
