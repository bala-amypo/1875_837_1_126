package com.example.demo;

public class TierUpgradeRule {

    private Long id;
    private String fromTier;
    private String toTier;
    private int minPoints;

    public TierUpgradeRule() {}

    public TierUpgradeRule(Long id, String fromTier, String toTier, int minPoints) {
        this.id = id;
        this.fromTier = fromTier;
        this.toTier = toTier;
        this.minPoints = minPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromTier() {
        return fromTier;
    }

    public void setFromTier(String fromTier) {
        this.fromTier = fromTier;
    }

    public String getToTier() {
        return toTier;
    }

    public void setToTier(String toTier) {
        this.toTier = toTier;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }
}
