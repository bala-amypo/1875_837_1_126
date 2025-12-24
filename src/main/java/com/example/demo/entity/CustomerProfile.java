package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_profile")
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private boolean active;

    public String getEmail() {
        return email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
