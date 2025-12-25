package com.example.demo.service.impl;

import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {

    private final TierUpgradeRuleRepository repository;

    public TierUpgradeRuleServiceImpl(TierUpgradeRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public TierUpgradeRule createRule(TierUpgradeRule rule) {
        if (rule.getMinSpend() < 0 || rule.getMinVisits() < 0) {
            throw new IllegalArgumentException("Thresholds must be non-negative");
        }
        if (repository.findByFromTierAndToTier(rule.getFromTier(), rule.getToTier()).isPresent()) {
             throw new IllegalArgumentException("Rule already exists for these tiers");
        }
        if (rule.isActive() == null) rule.setActive(true);
        return repository.save(rule);
    }

    @Override
    public TierUpgradeRule updateRule(Long id, TierUpgradeRule updatedRule) {
        TierUpgradeRule existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));
        
        existing.setFromTier(updatedRule.getFromTier());
        existing.setToTier(updatedRule.getToTier());
        existing.setMinSpend(updatedRule.getMinSpend());
        existing.setMinVisits(updatedRule.getMinVisits());
        if(updatedRule.isActive() != null) existing.setActive(updatedRule.isActive());
        
        return repository.save(existing);
    }

    @Override
    public List<TierUpgradeRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public Optional<TierUpgradeRule> getRule(String fromTier, String toTier) {
        return repository.findByFromTierAndToTier(fromTier, toTier);
    }

    @Override
    public List<TierUpgradeRule> getAllRules() {
        return repository.findAll();
    }
}