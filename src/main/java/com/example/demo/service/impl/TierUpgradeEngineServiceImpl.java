package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {
    private final CustomerProfileRepository customerRepo;
    private final PurchaseRecordRepository purchaseRepo;
    private final VisitRecordRepository visitRepo;
    private final TierUpgradeRuleRepository ruleRepo;
    private final TierHistoryRecordRepository historyRepo;

    public TierUpgradeEngineServiceImpl(CustomerProfileRepository customerRepo, PurchaseRecordRepository purchaseRepo,
                                        VisitRecordRepository visitRepo, TierUpgradeRuleRepository ruleRepo,
                                        TierHistoryRecordRepository historyRepo) {
        this.customerRepo = customerRepo; this.purchaseRepo = purchaseRepo;
        this.visitRepo = visitRepo; this.ruleRepo = ruleRepo; this.historyRepo = historyRepo;
    }

    @Override
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        double totalSpend = purchaseRepo.findByCustomerId(customerId).stream().mapToDouble(PurchaseRecord::getAmount).sum();
        int totalVisits = visitRepo.findByCustomerId(customerId).size();

        List<TierUpgradeRule> activeRules = ruleRepo.findByActiveTrue();
        for (TierUpgradeRule rule : activeRules) {
            if (rule.getFromTier().equals(customer.getCurrentTier())) {
                if (totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits()) {
                    String oldTier = customer.getCurrentTier();
                    customer.setCurrentTier(rule.getToTier());
                    customerRepo.save(customer);

                    TierHistoryRecord history = new TierHistoryRecord(customerId, oldTier, rule.getToTier(), "Threshold met", null);
                    return historyRepo.save(history);
                }
            }
        }
        return null;
    }
    // Other history methods...
}