package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "customer_profiles")
public class CustomerProfile {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique Customer ID
    @Column(name = "customer_id", nullable = false, unique = true)
    private String customerId;

    // Full Name
    @Column(nullable = false)
    private String fullName;

    // Unique Email
    @Column(nullable = false, unique = true)
    private String email;

    // Unique Phone
    @Column(nullable = false, unique = true)
    private String phone;

    // Loyalty Tier
    @Column(nullable = false)
    private String currentTier;

    // Active Status
    @Column(nullable = false)
    private Boolean active;

    // Created Timestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

   
    public CustomerProfile() {
    }

   
    public CustomerProfile(
            String customerId,
            String fullName,
            String email,
            String phone,
            String currentTier,
            Boolean active,
            LocalDateTime createdAt
    ) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.currentTier = currentTier;
        this.active = active;
        this.createdAt = createdAt;
    }

    //  Default values before insert
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.currentTier == null || this.currentTier.isBlank()) {
            this.currentTier = "BRONZE";
        }

        if (this.active == null) {
            this.active = true;
        }
    }

    //  Getters & Setters

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(String currentTier) {
        this.currentTier = currentTier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
