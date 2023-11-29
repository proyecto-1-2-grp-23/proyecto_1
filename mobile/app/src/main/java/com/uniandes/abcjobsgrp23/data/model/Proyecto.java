package com.uniandes.abcjobsgrp23.data.model;

import com.google.gson.annotations.SerializedName;

public class Proyecto {

    private String conocimientosTecnicos;
    private String createdAt;
    private String descripcion;
    private String finishDate;
    private String habilidadesBlandas;
    private int id;
    private int idEmpresa;
    private String nombre;
    private String perfiles;
    private String startDate;

    public Proyecto(String conocimientosTecnicos, String createdAt, String descripcion, String finishDate, String habilidadesBlandas, int id, int idEmpresa, String nombre, String perfiles, String startDate) {
        this.conocimientosTecnicos = conocimientosTecnicos;
        this.createdAt = createdAt;
        this.descripcion = descripcion;
        this.finishDate = finishDate;
        this.habilidadesBlandas = habilidadesBlandas;
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.perfiles = perfiles;
        this.startDate = startDate;
    }

    // Agrega los getters y setters según sea necesario
    public String getConocimientosTecnicos() {
        return conocimientosTecnicos;
    }

    public void setConocimientosTecnicos(String conocimientosTecnicos) {
        this.conocimientosTecnicos = conocimientosTecnicos;
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

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getHabilidadesBlandas() {
        return habilidadesBlandas;
    }

    public void setHabilidadesBlandas(String habilidadesBlandas) {
        this.habilidadesBlandas = habilidadesBlandas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(String perfiles) {
        this.perfiles = perfiles;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
