package com.example.demo;

import java.time.LocalDateTime;

public class TierHistoryRecord {

    private Long id;
    private String fromTier;
    private String toTier;
    private LocalDateTime changedAt;
    private String reason;

    public TierHistoryRecord() {}

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

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
