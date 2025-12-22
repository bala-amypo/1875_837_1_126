package com.example.demo.repository;

import com.example.demo.model.TierUpgradeRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TierUpgradeRuleRepository
        extends JpaRepository<TierUpgradeRule, Long> {

    List<TierUpgradeRule> findByActiveTrue();

    List<TierUpgradeRule> findByFromTierAndActiveTrue(String fromTier);

    List<TierUpgradeRule> findByFromTierAndToTier(String fromTier, String toTier);
}
