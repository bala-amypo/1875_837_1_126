package com.example.demo.service.impl;

import com.example.demo.model.CustomerProfile;
import com.example.demo.model.PurchaseRecord;
import com.example.demo.model.VisitRecord;
import com.example.demo.model.TierHistoryRecord;
import com.example.demo.model.TierUpgradeRule;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.repository.TierHistoryRecordRepository;
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

    public TierUpgradeEngineServiceImpl(
            CustomerProfileRepository customerProfileRepository,
            PurchaseRecordRepository purchaseRecordRepository,
            VisitRecordRepository visitRecordRepository,
            TierUpgradeRuleRepository tierUpgradeRuleRepository,
            TierHistoryRecordRepository tierHistoryRecordRepository
    ) {
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

        // ==========================
        // Calculate total spend
        // ==========================
        List<PurchaseRecord> purchases =
                purchaseRecordRepository.findByCustomerId(customerId);

        double totalSpend = purchases.stream()
                .mapToDouble(PurchaseRecord::getAmount)
                .sum();

        // ==========================
        // Calculate total visits
        // ==========================
        List<VisitRecord> visits =
                visitRecordRepository.findByCustomerId(customerId);

        int totalVisits = visits.size();

        String currentTier = customer.getCurrentTier();

        // ==========================
        // Find active rules for current tier
        // ==========================
        List<TierUpgradeRule> rules =
                tierUpgradeRuleRepository.findByActiveTrue();

        for (TierUpgradeRule rule : rules) {

            if (!rule.getFromTier().equalsIgnoreCase(currentTier)) {
                continue;
            }

            boolean spendMet = totalSpend >= rule.getMinSpend();
            boolean visitsMet = totalVisits >= rule.getMinVisits();

            if (spendMet && visitsMet) {

                String oldTier = currentTier;
                String newTier = rule.getToTier();

                // Update customer tier
                customer.setCurrentTier(newTier);
                customerProfileRepository.save(customer);

                // Save history record
                TierHistoryRecord historyRecord =
                        new TierHistoryRecord(
                                customerId,
                                oldTier,
                                newTier,
                               "Upgraded from " + oldTier + " to " + newTier,
                                LocalDateTime.now()
                        );

                return tierHistoryRecordRepository.save(historyRecord);
            }
        }

        // No upgrade applicable
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
