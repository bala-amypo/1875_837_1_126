package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "tier_upgrade_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"from_tier", "to_tier"})
    }
)
public class TierUpgradeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_tier", nullable = false)
    private String fromTier;

    @Column(name = "to_tier", nullable = false)
    private String toTier;

    @Column(name = "min_spend", nullable = false)
    private Double minSpend;

    @Column(name = "min_visits", nullable = false)
    private Integer minVisits;

    @Column(nullable = false)
    private Boolean active = true;

    // No-arg constructor
    public TierUpgradeRule() {
    }

    // Parameterized constructor
    public TierUpgradeRule(
            String fromTier,
            String toTier,
            Double minSpend,
            Integer minVisits,
            Boolean active
    ) {
        this.fromTier = fromTier;
        this.toTier = toTier;
        this.minSpend = minSpend;
        this.minVisits = minVisits;
        this.active = active != null ? active : true;
    }

    // ===== Getters =====

    public Long getId() {
        return id;
    }

    public String getFromTier() {
        return fromTier;
    }

    public String getToTier() {
        return toTier;
    }

    public Double getMinSpend() {
        return minSpend;
    }

    public Integer getMinVisits() {
        return minVisits;
    }

    public Boolean getActive() {
        return active;
    }

    //  Setters

    public void setFromTier(String fromTier) {
        this.fromTier = fromTier;
    }

    public void setToTier(String toTier) {
        this.toTier = toTier;
    }

    public void setMinSpend(Double minSpend) {
        if (minSpend != null && minSpend < 0) {
            throw new IllegalArgumentException("minSpend must be >= 0");
        }
        this.minSpend = minSpend;
    }

    public void setMinVisits(Integer minVisits) {
        if (minVisits != null && minVisits < 0) {
            throw new IllegalArgumentException("minVisits must be >= 0");
        }
        this.minVisits = minVisits;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
