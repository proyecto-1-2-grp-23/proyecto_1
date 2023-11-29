package com.uniandes.abcjobsgrp23.data.model;

import java.util.List;

public class TecnicaPreguntas {

    private String createdAt;
    private String descripcion;
    private int dificultad;
    private int id;
    private int idProyecto;
    private List<PosibleRespuesta> respuestas;

    // Constructor, getters y setters
    public TecnicaPreguntas(String createdAt, String descripcion, int dificultad, int id, int idProyecto, List<PosibleRespuesta> respuestas) {
        this.createdAt = createdAt;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.id = id;
        this.idProyecto = idProyecto;
        this.respuestas = respuestas;
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

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public List<PosibleRespuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<PosibleRespuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
