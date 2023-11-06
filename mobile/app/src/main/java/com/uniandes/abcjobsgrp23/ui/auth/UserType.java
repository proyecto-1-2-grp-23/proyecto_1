package com.uniandes.abcjobsgrp23.ui.auth;

public class UserType {
    public static final String CANDIDATO = "CANDIDATO";
    public static final String EMPRESA = "EMPRESA";
    private static String userType = "";

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String type) {
        userType = type;
    }
}
