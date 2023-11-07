package com.uniandes.abcjobsgrp23.data.model;

import java.util.Objects;

public class Candidato {
    private Integer id;
    private Integer idUsuario;
    private String nombreCompleto;
    private Integer edad;
    private String telefono;
    private String correo;
    private String pais;
    private String ciudad;
    private String idiomas;
    private String rasgosPersonalidad;
    private String password;

    // Constructor
    // Constructor para crear candidatos
    public Candidato(String nombreCompleto, int edad, String telefono, String correo, String pais, String ciudad, String idiomas, String rasgosPersonalidad, String password) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.pais = pais;
        this.ciudad = ciudad;
        this.idiomas = idiomas;
        this.rasgosPersonalidad = rasgosPersonalidad;
        this.password = password;
    }

    // Constructor para consultar candidatos
    public Candidato(int id, String nombreCompleto, int edad, String telefono, String idiomas, String rasgosPersonalidad) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.telefono = telefono;
        this.idiomas = idiomas;
        this.rasgosPersonalidad = rasgosPersonalidad;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getRasgosPersonalidad() {
        return rasgosPersonalidad;
    }

    public void setRasgosPersonalidad(String rasgosPersonalidad) {
        this.rasgosPersonalidad = rasgosPersonalidad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Equals y HashCode (para comparaciones y colecciones)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidato candidato = (Candidato) o;
        return  Objects.equals(nombreCompleto, candidato.nombreCompleto) &&
                Objects.equals(edad, candidato.edad) &&
                Objects.equals(idiomas, candidato.idiomas) &&
                Objects.equals(rasgosPersonalidad, candidato.rasgosPersonalidad) &&
                Objects.equals(telefono, candidato.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreCompleto, edad, idiomas, rasgosPersonalidad, telefono);
    }

    // toString (para depuración y registro)
    @Override
    public String toString() {
        return "Candidato{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", idiomas='" + idiomas + '\'' +
                ", rasgosPersonalidad='" + rasgosPersonalidad + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
