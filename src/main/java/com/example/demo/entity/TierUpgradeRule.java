package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tier_upgrade_rules")
public class TierUpgradeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromTier;
    private String toTier;
    private double requiredSpend;
    private int requiredVisits;

    public TierUpgradeRule() {
    }

    public Long getId() {
        return id;
    }

    public String getFromTier() {
        return fromTier;
    }

    public String getToTier() {
        return toTier;
    }

    public double getRequiredSpend() {
        return requiredSpend;
    }

    public int getRequiredVisits() {
        return requiredVisits;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFromTier(String fromTier) {
        this.fromTier = fromTier;
    }

    public void setToTier(String toTier) {
        this.toTier = toTier;
    }

    public void setRequiredSpend(double requiredSpend) {
        this.requiredSpend = requiredSpend;
    }

    public void setRequiredVisits(int requiredVisits) {
        this.requiredVisits = requiredVisits;
    }
}
