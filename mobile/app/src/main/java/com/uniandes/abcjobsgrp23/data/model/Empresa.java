package com.uniandes.abcjobsgrp23.data.model;

import java.util.Objects;

public class Empresa {
    private Integer id;
    private String razonSocial;
    private String tipoEmpresa;
    private String correo;
    private String pais;
    private String ciudad;
    private String verticalesNegocio;
    private String password;

    // Constructor para crear empresas
    public Empresa(String razonSocial, String tipoEmpresa, String correo, String pais, String ciudad, String verticalesNegocio, String password) {
        this.razonSocial = razonSocial;
        this.tipoEmpresa = tipoEmpresa;
        this.correo = correo;
        this.pais = pais;
        this.ciudad = ciudad;
        this.verticalesNegocio = verticalesNegocio;
        this.password = password;
    }

    // Constructor para consultar empresas
    public Empresa(Integer id, String razonSocial, String tipoEmpresa, String verticalesNegocio) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.tipoEmpresa = tipoEmpresa;
        this.verticalesNegocio = verticalesNegocio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getVerticalesNegocio() {
        return verticalesNegocio;
    }

    public void setVerticalesNegocio(String verticalesNegocio) {
        this.verticalesNegocio = verticalesNegocio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
