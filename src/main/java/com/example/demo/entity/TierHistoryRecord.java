package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tier_history_records")
public class TierHistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oldTier;
    private String newTier;
    private String reason;
    private LocalDateTime changedAt;

    @ManyToOne
    private CustomerProfile customer;

    public TierHistoryRecord() {
    }

    @PrePersist
    public void onCreate() {
        this.changedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public CustomerProfile getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCustomer(CustomerProfile customer) {
        this.customer = customer;
    }
}
