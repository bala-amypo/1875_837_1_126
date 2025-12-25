package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    private final CustomerProfileRepository customerRepo;
    private final PurchaseRecordRepository purchaseRepo;
    private final VisitRecordRepository visitRepo;
    private final TierUpgradeRuleRepository ruleRepo;
    private final TierHistoryRecordRepository historyRepo;

    public TierUpgradeEngineServiceImpl(CustomerProfileRepository customerRepo,
                                        PurchaseRecordRepository purchaseRepo,
                                        VisitRecordRepository visitRepo,
                                        TierUpgradeRuleRepository ruleRepo,
                                        TierHistoryRecordRepository historyRepo) {
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
        this.visitRepo = visitRepo;
        this.ruleRepo = ruleRepo;
        this.historyRepo = historyRepo;
    }

    @Override
    @Transactional
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        // 1. Calculate Aggregates
        List<PurchaseRecord> purchases = purchaseRepo.findByCustomerId(customerId);
        double totalSpend = purchases.stream().mapToDouble(PurchaseRecord::getAmount).sum();

        List<VisitRecord> visits = visitRepo.findByCustomerId(customerId);
        int totalVisits = visits.size();

        // 2. Find matching rules for current tier
        List<TierUpgradeRule> rules = ruleRepo.findByActiveTrue();
        
        for (TierUpgradeRule rule : rules) {
            if (rule.getFromTier().equalsIgnoreCase(customer.getCurrentTier())) {
                // 3. Check thresholds
                if (totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits()) {
                    
                    // 4. Perform Upgrade
                    String oldTier = customer.getCurrentTier();
                    String newTier = rule.getToTier();
                    
                    customer.setCurrentTier(newTier);
                    customerRepo.save(customer);

                    // 5. Log History
                    TierHistoryRecord history = new TierHistoryRecord(
                            customer, oldTier, newTier, 
                            "Upgrade triggered: Spend=" + totalSpend + ", Visits=" + totalVisits
                    );
                    return historyRepo.save(history);
                }
            }
        }
        return null; // No upgrade occurred
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        return historyRepo.findByCustomerId(customerId);
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return historyRepo.findAll();
    }
}