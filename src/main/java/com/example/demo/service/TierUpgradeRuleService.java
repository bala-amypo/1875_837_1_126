package com.example.demo.service;
import com.example.demo.entity.TierUpgradeRule;
import java.util.List;

public interface TierUpgradeRuleService {
    TierUpgradeRule createRule(TierUpgradeRule rule);
    TierUpgradeRule updateRule(Long id, TierUpgradeRule updatedRule);
    List<TierUpgradeRule> getActiveRules();
    TierUpgradeRule getRule(String fromTier, String toTier);
    List<TierUpgradeRule> getAllRules();
    // Removed getRuleByFromTier if it existed, as it's not in the helper doc requirements
}