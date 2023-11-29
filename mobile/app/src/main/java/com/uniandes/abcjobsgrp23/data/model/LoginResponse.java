package com.uniandes.abcjobsgrp23.data.model;


public class LoginResponse {
    private int id;
    private String message;
    private String token;

    // Constructor vacío necesario para la deserialización con Gson
    public LoginResponse() {
    }

    public LoginResponse(int id, String message, String token) {
        this.id = id;
        this.message = message;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
