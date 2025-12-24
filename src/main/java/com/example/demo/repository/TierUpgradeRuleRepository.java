package com.example.demo.repository;

import com.example.demo.entity.TierUpgradeRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface TierUpgradeRuleRepository extends JpaRepository<TierUpgradeRule, Long> {
    // Required method: Find specific rule for a from-tier to to-tier transition
    Optional<TierUpgradeRule> findByFromTierAndToTier(String fromTier, String toTier);

    // Required method: List all currently active rules
    List<TierUpgradeRule> findByActiveTrue();
}