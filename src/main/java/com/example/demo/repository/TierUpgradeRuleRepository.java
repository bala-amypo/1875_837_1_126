package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TierUpgradeRule;

public interface TierUpgradeRuleRepository extends JpaRepository<TierUpgradeRule, Long> {

    Optional<TierUpgradeRule> findByFromTier(String fromTier);
}
