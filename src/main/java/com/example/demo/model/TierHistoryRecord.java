package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tier_history_records")
public class TierHistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "old_tier", nullable = false)
    private String oldTier;

    @Column(name = "new_tier", nullable = false)
    private String newTier;

    @Column(nullable = false)
    private String reason;

    @Column(name = "changed_at", nullable = false, updatable = false)
    private LocalDateTime changedAt;

    // No-arg constructor
    public TierHistoryRecord() {
    }

    // Parameterized constructor
    public TierHistoryRecord(
            Long customerId,
            String oldTier,
            String newTier,
            String reason,
            LocalDateTime changedAt
    ) {
        this.customerId = customerId;
        this.oldTier = oldTier;
        this.newTier = newTier;
        this.reason = reason;
        this.changedAt = changedAt;
    }

    // Auto-generate timestamp before persist
    @PrePersist
    protected void onCreate() {
        this.changedAt = LocalDateTime.now();
    }

    // Getters 

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getOldTier() {
        return oldTier;
    }

    public String getNewTier() {
        return newTier;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    //  Setters 

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setOldTier(String oldTier) {
        this.oldTier = oldTier;
    }

    public void setNewTier(String newTier) {
        this.newTier = newTier;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
