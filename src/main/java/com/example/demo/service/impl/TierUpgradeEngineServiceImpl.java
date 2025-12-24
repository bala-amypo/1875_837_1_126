package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {
    private final CustomerProfileRepository customerProfileRepository;
    private final PurchaseRecordRepository purchaseRecordRepository;
    private final VisitRecordRepository visitRecordRepository;
    private final TierUpgradeRuleRepository tierUpgradeRuleRepository;
    private final TierHistoryRecordRepository tierHistoryRecordRepository;

    // Strict constructor order required for automated testing
    public TierUpgradeEngineServiceImpl(
            CustomerProfileRepository customerProfileRepository,
            PurchaseRecordRepository purchaseRecordRepository,
            VisitRecordRepository visitRecordRepository,
            TierUpgradeRuleRepository tierUpgradeRuleRepository,
            TierHistoryRecordRepository tierHistoryRecordRepository) {
        this.customerProfileRepository = customerProfileRepository;
        this.purchaseRecordRepository = purchaseRecordRepository;
        this.visitRecordRepository = visitRecordRepository;
        this.tierUpgradeRuleRepository = tierUpgradeRuleRepository;
        this.tierHistoryRecordRepository = tierHistoryRecordRepository;
    }

    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        CustomerProfile customer = customerProfileRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        double totalSpend = purchaseRecordRepository.findByCustomerId(customerId).stream()
                .mapToDouble(PurchaseRecord::getAmount).sum();
        int totalVisits = visitRecordRepository.findByCustomerId(customerId).size();

        String currentTier = customer.getCurrentTier();
        List<TierUpgradeRule> activeRules = tierUpgradeRuleRepository.findByActiveTrue();

        for (TierUpgradeRule rule : activeRules) {
            if (rule.getFromTier().equalsIgnoreCase(currentTier)) {
                if (totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits()) {
                    customer.setCurrentTier(rule.getToTier());
                    customerProfileRepository.save(customer);

                    TierHistoryRecord history = new TierHistoryRecord();
                    history.setCustomerId(customerId);
                    history.setOldTier(currentTier);
                    history.setNewTier(rule.getToTier());
                    history.setReason("Thresholds met: Spend=" + totalSpend + ", Visits=" + totalVisits);
                    return tierHistoryRecordRepository.save(history);
                }
            }
        }
        return null;
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return tierHistoryRecordRepository.findByCustomerId(customerId);
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return tierHistoryRecordRepository.findAll();
    }
}