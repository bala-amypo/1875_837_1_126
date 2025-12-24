package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TierUpgradeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromTier;
    private String toTier;
    private double minSpend;
    private int minVisits;
    private boolean active;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFromTier() { return fromTier; }
    public void setFromTier(String fromTier) { this.fromTier = fromTier; }

    public String getToTier() { return toTier; }
    public void setToTier(String toTier) { this.toTier = toTier; }

    public double getMinSpend() { return minSpend; }
    public void setMinSpend(double minSpend) { this.minSpend = minSpend; }

    public int getMinVisits() { return minVisits; }
    public void setMinVisits(int minVisits) { this.minVisits = minVisits; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
