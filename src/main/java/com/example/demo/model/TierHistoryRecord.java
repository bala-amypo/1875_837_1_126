package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tier_history_records")
public class TierHistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerProfile customer;

    private String oldTier;
    private String newTier;
    private String reason;
    private LocalDateTime changedAt;

    public TierHistoryRecord() {}

    public TierHistoryRecord(CustomerProfile customer, String oldTier, String newTier, String reason) {
        this.customer = customer;
        this.oldTier = oldTier;
        this.newTier = newTier;
        this.reason = reason;
    }

    @PrePersist
    protected void onCreate() {
        this.changedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public CustomerProfile getCustomer() { return customer; }
    public void setCustomer(CustomerProfile customer) { this.customer = customer; }
    public String getOldTier() { return oldTier; }
    public void setOldTier(String oldTier) { this.oldTier = oldTier; }
    public String getNewTier() { return newTier; }
    public void setNewTier(String newTier) { this.newTier = newTier; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }
}