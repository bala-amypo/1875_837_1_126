package com.example.demo.service.impl;
import com.example.demo.model.TierUpgradeRule;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {
    private final TierUpgradeRuleRepository repository;
    public TierUpgradeRuleServiceImpl(TierUpgradeRuleRepository repository) { this.repository = repository; }

    public TierUpgradeRule createRule(TierUpgradeRule rule) {
        if (rule.getMinSpend() < 0 || rule.getMinVisits() < 0) throw new IllegalArgumentException("Invalid rule values");
        return repository.save(rule);
    }
    public TierUpgradeRule updateRule(Long id, TierUpgradeRule updatedRule) {
        TierUpgradeRule rule = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Rule not found"));
        rule.setFromTier(updatedRule.getFromTier());
        rule.setToTier(updatedRule.getToTier());
        rule.setMinSpend(updatedRule.getMinSpend());
        rule.setMinVisits(updatedRule.getMinVisits());
        rule.setActive(updatedRule.getActive());
        return repository.save(rule);
    }
    public List<TierUpgradeRule> getActiveRules() { return repository.findByActiveTrue(); }
    public TierUpgradeRule getRule(String fromTier, String toTier) {
        return repository.findByFromTierAndToTier(fromTier, toTier).orElseThrow(() -> new NoSuchElementException("Rule not found"));
    }
    public List<TierUpgradeRule> getAllRules() { return repository.findAll(); }
}