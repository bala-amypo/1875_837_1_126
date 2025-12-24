package com.example.demo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.repository.TierUpgradeRuleRepository;
import com.example.demo.service.TierUpgradeRuleService;

@Service
@Transactional
public class TierUpgradeRuleServiceImpl implements TierUpgradeRuleService {

    private final TierUpgradeRuleRepository ruleRepo;

    public TierUpgradeRuleServiceImpl(TierUpgradeRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public TierUpgradeRule createRule(TierUpgradeRule rule) {
        if (rule.getMinSpend() < 0 || rule.getMinVisits() < 0) {
            throw new IllegalArgumentException("minSpend and minVisits must be >= 0");
        }
        return ruleRepo.save(rule);
    }

    @Override
    public TierUpgradeRule updateRule(Long id, TierUpgradeRule updatedRule) {
        TierUpgradeRule existing = ruleRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));

        existing.setFromTier(updatedRule.getFromTier());
        existing.setToTier(updatedRule.getToTier());
        existing.setMinSpend(updatedRule.getMinSpend());
        existing.setMinVisits(updatedRule.getMinVisits());
        existing.setActive(updatedRule.getActive());

        return ruleRepo.save(existing);
    }

    @Override
    public List<TierUpgradeRule> getAllRules() {
        return ruleRepo.findAll();
    }

    @Override
    public TierUpgradeRule getRuleByFromTier(String fromTier) {
        return ruleRepo.findByFromTier(fromTier)
                .orElseThrow(() -> new NoSuchElementException("Rule not found"));
    }
}
