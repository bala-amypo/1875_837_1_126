package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_records")
public class PurchaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key reference to CustomerProfile
    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private String storeLocation;

    // No-arg constructor
    public PurchaseRecord() {
    }

    // Parameterized constructor
    public PurchaseRecord(Long customerId, Double amount,
                          LocalDate purchaseDate, String storeLocation) {
        this.customerId = customerId;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.storeLocation = storeLocation;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }
}
