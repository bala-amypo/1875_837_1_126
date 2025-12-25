package com.example.demo;
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
        CustomerProfile customer = customerRepo.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
        double totalSpend = purchaseRepo.findByCustomerId(customerId).stream().mapToDouble(PurchaseRecord::getAmount).sum();
        long totalVisits = visitRepo.findByCustomerId(customerId).size();
        for (TierUpgradeRule rule : ruleRepo.findByActiveTrue()) {
            if (rule.getFromTier().equalsIgnoreCase(customer.getCurrentTier())) {
                if (totalSpend >= rule.getMinSpend() && totalVisits >= rule.getMinVisits()) {
                    String oldTier = customer.getCurrentTier();
                    customer.setCurrentTier(rule.getToTier());
                    customerRepo.save(customer);
                    return historyRepo.save(new TierHistoryRecord(customerId, oldTier, rule.getToTier(), "Upgrade Rule Met", LocalDateTime.now()));
                }
            }
        }
        return null;
    }
    public List<TierHistoryRecord> getHistoryByCustomer(Long customerId) { return historyRepo.findByCustomerId(customerId); }
    public List<TierHistoryRecord> getAllHistory() { return historyRepo.findAll(); }
}