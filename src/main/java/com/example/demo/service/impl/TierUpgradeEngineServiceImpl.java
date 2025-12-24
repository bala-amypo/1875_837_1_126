package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {
    private final CustomerProfileRepository customerRepo;
    private final PurchaseRecordRepository purchaseRepo;
    private final VisitRecordRepository visitRepo;
    private final TierUpgradeRuleRepository ruleRepo;
    private final TierHistoryRecordRepository historyRepo;

    public TierUpgradeEngineServiceImpl(CustomerProfileRepository customerRepo, PurchaseRecordRepository purchaseRepo, VisitRecordRepository visitRepo, TierUpgradeRuleRepository ruleRepo, TierHistoryRecordRepository historyRepo) {
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
        this.visitRepo = visitRepo;
        this.ruleRepo = ruleRepo;
        this.historyRepo = historyRepo;
    }

    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        List<PurchaseRecord> purchases = purchaseRepo.findByCustomerId(customerId);
        List<VisitRecord> visits = visitRepo.findByCustomerId(customerId);

        double totalSpend = purchases.stream().mapToDouble(PurchaseRecord::getAmount).sum();
        long totalVisits = visits.size();

        List<TierUpgradeRule> activeRules = ruleRepo.findByActiveTrue();

        for (TierUpgradeRule rule : activeRules) {
            if (rule.getFromTier().equalsIgnoreCase(customer.getCurrentTier())) {
                if (totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits()) {
                    // Upgrade
                    String oldTier = customer.getCurrentTier();
                    customer.setCurrentTier(rule.getToTier());
                    customerRepo.save(customer);

                    TierHistoryRecord history = new TierHistoryRecord(
                            customerId, oldTier, rule.getToTier(), "Upgrade Rule Met", LocalDateTime.now()
                    );
                    return historyRepo.save(history);
                }
            }
        }
        return null;
    }

    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return historyRepo.findByCustomerId(customerId);
    }
    
    public List<TierHistoryRecord> getAllHistory() { return historyRepo.findAll(); }
}