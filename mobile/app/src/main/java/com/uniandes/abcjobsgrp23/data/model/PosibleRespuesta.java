package com.uniandes.abcjobsgrp23.data.model;

public class PosibleRespuesta {

    private String createdAt;
    private String descripcion;
    private boolean esCorrecta;
    private int id;
    private int idPregunta;

    // Constructor, getters y setters
    public PosibleRespuesta(String createdAt, String descripcion, boolean esCorrecta, int id, int idPregunta) {
        this.createdAt = createdAt;
        this.descripcion = descripcion;
        this.esCorrecta = esCorrecta;
        this.id = id;
        this.idPregunta = idPregunta;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
}
