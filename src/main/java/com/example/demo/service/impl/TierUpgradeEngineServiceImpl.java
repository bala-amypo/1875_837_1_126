package com.example.demo.service.impl;

import com.example.demo.entity.CustomerProfile;
import com.example.demo.entity.PurchaseRecord;
import com.example.demo.entity.TierHistoryRecord;
import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.entity.VisitRecord;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.repository.TierHistoryRecordRepository;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    private final CustomerProfileRepository customerProfileRepository;
    private final PurchaseRecordRepository purchaseRecordRepository;
    private final VisitRecordRepository visitRecordRepository;
    private final TierUpgradeRuleRepository tierUpgradeRuleRepository;
    private final TierHistoryRecordRepository tierHistoryRecordRepository;

    /**
     * Constructor Injection - Order must be EXACTLY as per technical constraints:
     * 1. CustomerProfileRepository
     * 2. PurchaseRecordRepository
     * 3. VisitRecordRepository
     * 4. TierUpgradeRuleRepository
     * 5. TierHistoryRecordRepository
     */
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
        // 1. Fetch customer or throw exact exception message
        CustomerProfile customer = customerProfileRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        // 2. Calculate total spend
        List<PurchaseRecord> purchases = purchaseRecordRepository.findByCustomerId(customerId);
        double totalSpend = purchases.stream()
                .mapToDouble(PurchaseRecord::getAmount)
                .sum();

        // 3. Calculate total visits
        List<VisitRecord> visits = visitRecordRepository.findByCustomerId(customerId);
        int totalVisits = visits.size();

        // 4. Get current tier and matching active rules
        String currentTier = customer.getCurrentTier();
        List<TierUpgradeRule> activeRules = tierUpgradeRuleRepository.findByActiveTrue();

        // 5. Evaluate rules
        for (TierUpgradeRule rule : activeRules) {
            if (rule.getFromTier().equalsIgnoreCase(currentTier)) {
                if (totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits()) {
                    
                    String oldTier = customer.getCurrentTier();
                    String newTier = rule.getToTier();

                    // Update Customer
                    customer.setCurrentTier(newTier);
                    customerProfileRepository.save(customer);

                    // Create History Record
                    TierHistoryRecord history = new TierHistoryRecord();
                    history.setCustomerId(customerId);
                    history.setOldTier(oldTier);
                    history.setNewTier(newTier);
                    history.setReason("Upgrade threshold met: Spend=" + totalSpend + ", Visits=" + totalVisits);
                    // createdAt/changedAt handled by @PrePersist in entity
                    
                    return tierHistoryRecordRepository.save(history);
                }
            }
        }

        return null; // Return null if no upgrade criteria met as per Page 12
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