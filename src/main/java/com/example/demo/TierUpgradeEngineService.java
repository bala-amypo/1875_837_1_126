package com.example.demo;
import java.util.List;

public interface TierUpgradeEngineService {
    TierHistoryRecord evaluateAndUpgradeTier(Long customerId);
    List<TierHistoryRecord> getHistoryByCustomer(Long customerId);
    List<TierHistoryRecord> getAllHistory();
}