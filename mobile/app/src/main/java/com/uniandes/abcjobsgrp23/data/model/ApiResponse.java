package com.uniandes.abcjobsgrp23.data.model;



public class ApiResponse {
    private String createdAt;
    private int id;

    public ApiResponse() {
        // Constructor vacío necesario para deserialización
    }

    public ApiResponse(String createdAt, int id) {
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

