package com.uniandes.abcjobsgrp23.data.model;



public class RecordTecnicalInterview {
    private String pregunta;
    private String calificacion;

    public RecordTecnicalInterview(String title, String description) {
        this.pregunta = title;
        this.calificacion = description;
    }

    public String getTitle() {
        return pregunta;
    }

    public String getDescription() {
        return calificacion;
    }
}


