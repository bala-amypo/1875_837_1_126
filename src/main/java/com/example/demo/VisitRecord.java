package com.example.demo;

import java.time.LocalDateTime;

public class VisitRecord {

    private Long id;
    private LocalDateTime visitTime;

    public VisitRecord() {}

    public VisitRecord(Long id, LocalDateTime visitTime) {
        this.id = id;
        this.visitTime = visitTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }
}
