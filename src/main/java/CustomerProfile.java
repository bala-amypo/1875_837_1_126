package com.example.demo;

public class CustomerProfile {

    private Long id;
    private String name;
    private String email;
    private String tier;
    private int points;

    public CustomerProfile() {}

    public CustomerProfile(Long id, String name, String email, String tier, int points) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tier = tier;
        this.points = points;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTier() { return tier; }
    public void setTier(String tier) { this.tier = tier; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
