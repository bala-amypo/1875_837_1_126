package com.example.demo.service.impl;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {

    private final TierUpgradeRuleRepository tierUpgradeRuleRepository;

    public TierUpgradeRuleServiceImpl(TierUpgradeRuleRepository tierUpgradeRuleRepository) {
        this.tierUpgradeRuleRepository = tierUpgradeRuleRepository;
    }

    @Override
    public TierUpgradeRule createRule(TierUpgradeRule rule) {
        validateRule(rule);
        return tierUpgradeRuleRepository.save(rule);
    }

    @Override
    public TierUpgradeRule updateRule(Long id, TierUpgradeRule updatedRule) {
        TierUpgradeRule existingRule = tierUpgradeRuleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));

        validateRule(updatedRule);

        existingRule.setFromTier(updatedRule.getFromTier());
        existingRule.setToTier(updatedRule.getToTier());
        existingRule.setMinSpend(updatedRule.getMinSpend());
        existingRule.setMinVisits(updatedRule.getMinVisits());
        existingRule.setActive(updatedRule.getActive());

        return tierUpgradeRuleRepository.save(existingRule);
    }

    @Override
    public List<TierUpgradeRule> getActiveRules() {
        return tierUpgradeRuleRepository.findByActiveTrue();
    }

   @Override
public TierUpgradeRule getRule(String fromTier, String toTier) {

    List<TierUpgradeRule> rules =
            tierUpgradeRuleRepository.findByFromTierAndToTier(fromTier, toTier);

    if (rules.isEmpty()) {
        throw new NoSuchElementException("Rule not found");
    }

    return rules.get(0); // unique fromTier + toTier
}


    @Override
    public List<TierUpgradeRule> getAllRules() {
        return tierUpgradeRuleRepository.findAll();
    }

    // ==========================
    // Validation
    // ==========================
    private void validateRule(TierUpgradeRule rule) {
        if (rule.getMinSpend() == null || rule.getMinSpend() < 0) {
            throw new IllegalArgumentException("minSpend must be >= 0");
        }
        if (rule.getMinVisits() == null || rule.getMinVisits() < 0) {
            throw new IllegalArgumentException("minVisits must be >= 0");
        }
    }
}
