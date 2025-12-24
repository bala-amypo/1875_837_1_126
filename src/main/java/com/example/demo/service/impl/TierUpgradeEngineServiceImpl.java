package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.CustomerProfile;
import com.example.demo.entity.PurchaseRecord;
import com.example.demo.entity.TierHistoryRecord;
import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.repository.CustomerProfileRepository;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.repository.TierHistoryRecordRepository;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.repository.VisitRecordRepository;
import com.example.demo.entity.VisitRecord;
import com.example.demo.service.TierUpgradeEngineService;

@Service
@Transactional
public class TierUpgradeEngineServiceImpl implements TierUpgradeEngineService {

    private final CustomerProfileRepository customerRepo;
    private final PurchaseRecordRepository purchaseRepo;
    private final VisitRecordRepository visitRepo;
    private final TierUpgradeRuleRepository ruleRepo;
    private final TierHistoryRecordRepository historyRepo;

    public TierUpgradeEngineServiceImpl(
            CustomerProfileRepository customerRepo,
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
    public TierHistoryRecord evaluateAndUpgradeTier(Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        List<PurchaseRecord> purchases = purchaseRepo.findByCustomer(customer);
        List<VisitRecord> visits = visitRepo.findByCustomer(customer);

        double totalSpend = purchases.stream().mapToDouble(PurchaseRecord::getAmount).sum();
        int totalVisits = visits.size();
        String currentTier = customer.getCurrentTier();

        // Find rules that match current tier
        List<TierUpgradeRule> rules = ruleRepo.findAll().stream()
                .filter(TierUpgradeRule::getActive)
                .filter(r -> r.getFromTier().equalsIgnoreCase(currentTier))
                .toList();

        for (TierUpgradeRule rule : rules) {
            if (totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits()) {
                String oldTier = customer.getCurrentTier();
                customer.setCurrentTier(rule.getToTier());
                customerRepo.save(customer);

                TierHistoryRecord history = new TierHistoryRecord();
                history.setCustomer(customer);
                history.setOldTier(oldTier);
                history.setNewTier(rule.getToTier());
                history.setReason("Automatic upgrade based on spend and visits");
                history.setChangedAt(LocalDateTime.now());

                return historyRepo.save(history);
            }
        }

        // No upgrade criteria met
        return null;
    }

    @Override
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        return historyRepo.findByCustomer(customer);
    }

    @Override
    public List<TierHistoryRecord> getAllHistory() {
        return historyRepo.findAll();
    }
}
