package com.uniandes.abcjobsgrp23.data.model;


public class UserCredential {

    private String correo;
    private String contraseña;

    public UserCredential(String username, String password) {
        this.correo = username;
        this.contraseña = password;
    }

    public String getUsername() {
        return correo;
    }

    public String getPassword() {
        return contraseña;
    }

    public void setUsername(String username) {
        this.correo = username;
    }

    public void setPassword(String password) {
        this.contraseña = password;
    }

}
