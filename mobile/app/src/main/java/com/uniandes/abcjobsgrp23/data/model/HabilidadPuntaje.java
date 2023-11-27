package com.uniandes.abcjobsgrp23.data.model;


public class HabilidadPuntaje {

    private String habilidad;
    private int puntaje;

    public HabilidadPuntaje() {
        // Constructor vacío necesario para deserialización
    }

    public HabilidadPuntaje(String habilidad, int puntaje) {
        this.habilidad = habilidad;
        this.puntaje = puntaje;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
