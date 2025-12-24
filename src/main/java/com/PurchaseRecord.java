package com.example.demo;

public class PurchaseRecord {

    private Long id;
    private double amount;
    private String category;

    public PurchaseRecord() {}

    public PurchaseRecord(Long id, double amount, String category) {
        this.id = id;
        this.amount = amount;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
