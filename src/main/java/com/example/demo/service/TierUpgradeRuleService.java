package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TierUpgradeRule;

public interface TierUpgradeRuleService {

    TierUpgradeRule createRule(TierUpgradeRule rule);

    TierUpgradeRule updateRule(Long id, TierUpgradeRule updatedRule);

    List<TierUpgradeRule> getActiveRules();

    TierUpgradeRule getRule(String fromTier, String toTier);

    List<TierUpgradeRule> getAllRules();
}
