package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visit_records")
public class VisitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key → CustomerProfile.id
    @Column(nullable = false)
    private Long customerId;

    private LocalDate visitDate;

    private String channel;

    // No-arg constructor
    public VisitRecord() {
    }

    // Parameterized constructor
    public VisitRecord(Long customerId, LocalDate visitDate, String channel) {
        this.customerId = customerId;
        this.visitDate = visitDate;
        setChannel(channel); // validation
    }

    // Getter & Setter methods

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getChannel() {
        return channel;
    }

    // Channel validation rule
    public void setChannel(String channel) {
        if (!"STORE".equalsIgnoreCase(channel)
                && !"APP".equalsIgnoreCase(channel)
                && !"WEB".equalsIgnoreCase(channel)) {
            throw new IllegalArgumentException("Invalid channel");
        }
        this.channel = channel.toUpperCase();
    }
}
