package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "visit_records")
public class VisitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private CustomerProfile customer;

    @Column(nullable = false)
    private String channel;

    @Column(nullable = false)
    private LocalDateTime visitTime;

    public VisitRecord() {
    }

    public VisitRecord(CustomerProfile customer, String channel) {
        this.customer = customer;
        this.channel = channel;
        this.visitTime = LocalDateTime.now();
    }

    @PrePersist
    public void onCreate() {
        if (visitTime == null) {
            visitTime = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public String getChannel() {
        return channel;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(CustomerProfile customer) {
        this.customer = customer;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }
}
