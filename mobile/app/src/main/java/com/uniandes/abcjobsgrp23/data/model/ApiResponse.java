package com.uniandes.abcjobsgrp23.data.model;

import java.util.Date;

public class ApiResponse {
    private Date createdAt;
    private int id;

    public ApiResponse() {
        // Constructor vacío necesario para deserialización
    }

    public ApiResponse(Date createdAt, int id) {
        this.createdAt = createdAt;
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

