package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_records")
public class PurchaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(nullable = false)
    private double amount;

    @ManyToOne(optional = false)
    private CustomerProfile customer;

    @Column(nullable = false)
    private LocalDateTime purchaseTime;

    public PurchaseRecord() {
    }

    public PurchaseRecord(double amount, CustomerProfile customer) {
        this.amount = amount;
        this.customer = customer;
        this.purchaseTime = LocalDateTime.now();
    }

    @PrePersist
    public void onCreate() {
        if (purchaseTime == null) {
            purchaseTime = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCustomer(CustomerProfile customer) {
        this.customer = customer;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
