package com.uniandes.abcjobsgrp23.data.model;


public class RecordEntrevista {
    private String title;
    private String description;

    public RecordEntrevista(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
