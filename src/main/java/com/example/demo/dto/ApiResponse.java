package com.example.demo.dto;

public class ApiResponse {
    public boolean success;
    public String message;
    public Object data;

    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}