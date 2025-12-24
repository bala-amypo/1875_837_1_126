package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TierUpgradeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double minSpend;
    private int minVisits;
    private boolean active;

    public double getMinSpend() { return minSpend; }
    public void setMinSpend(double minSpend) { this.minSpend = minSpend; }

    public int getMinVisits() { return minVisits; }
    public void setMinVisits(int minVisits) { this.minVisits = minVisits; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
