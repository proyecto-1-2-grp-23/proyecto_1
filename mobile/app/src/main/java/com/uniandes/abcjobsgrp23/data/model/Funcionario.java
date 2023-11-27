package com.uniandes.abcjobsgrp23.data.model;

import java.util.Objects;

public class Funcionario {

    private String ciudad;
    private String correo;
    private Integer id;
    private String pais;

    // Constructor vacío requerido por Retrofit
    public Funcionario() {
    }

    public Funcionario(String ciudad, String correo, Integer id, String pais) {
        this.ciudad = ciudad;
        this.correo = correo;
        this.id = id;
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(ciudad, that.ciudad) &&
                Objects.equals(correo, that.correo) &&
                Objects.equals(id, that.id) &&
                Objects.equals(pais, that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ciudad, correo, id, pais);
    }
}
