package com.uniandes.abcjobsgrp23.data.model;

public class Proyecto {
    private String nombre;
    private String descripcion;
    private Integer empresaID;

    public Proyecto(String nombre, String descripcion, Integer empresaID) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.empresaID = empresaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEmpresaID() {
        return empresaID;
    }

    public void setEmpresaID(Integer empresaID) {
        this.empresaID = empresaID;
    }
}
