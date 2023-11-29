package com.uniandes.abcjobsgrp23.data.model;

import java.util.List;

public class ResultadosDesempeno {

    private int idPrueba;
    private int idCandidato;
    private int idEmpresa;
    private List<HabilidadPuntaje> habilidades;
    private String habilidad;
    private int puntaje;

    public ResultadosDesempeno() {
        // Constructor vacío necesario para deserialización
    }

    public ResultadosDesempeno(int idPrueba, int idCandidato, int idEmpresa, List<HabilidadPuntaje> habilidades) {
        this.idPrueba = idPrueba;
        this.idCandidato = idCandidato;
        this.idEmpresa = idEmpresa;
        this.habilidades = habilidades;
    }

    public ResultadosDesempeno(int idPrueba, int idCandidato, int idEmpresa, String habilidad, int puntaje) {
        this.idPrueba = idPrueba;
        this.idCandidato = idCandidato;
        this.idEmpresa = idEmpresa;
        this.habilidad = habilidad;
        this.puntaje = puntaje;
    }

    public int getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(int idPrueba) {
        this.idPrueba = idPrueba;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<HabilidadPuntaje> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<HabilidadPuntaje> habilidades) {
        this.habilidades = habilidades;
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
