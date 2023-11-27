package com.uniandes.abcjobsgrp23.data.model;

import java.util.List;

public class ResultadosDesempeno {

    private int idPrueba;
    private int idCandidato;
    private int idEmpresa;
    private List<HabilidadPuntaje> habilidades;
    private HabilidadPuntaje habilidad;

    public ResultadosDesempeno() {
        // Constructor vacío necesario para deserialización
    }

    public ResultadosDesempeno(int idPrueba, int idCandidato, int idEmpresa, List<HabilidadPuntaje> habilidades) {
        this.idPrueba = idPrueba;
        this.idCandidato = idCandidato;
        this.idEmpresa = idEmpresa;
        this.habilidades = habilidades;
    }

    public ResultadosDesempeno(int idPrueba, int idCandidato, int idEmpresa, HabilidadPuntaje habilidad) {
        this.idPrueba = idPrueba;
        this.idCandidato = idCandidato;
        this.idEmpresa = idEmpresa;
        this.habilidad = habilidad;
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

    public HabilidadPuntaje getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(HabilidadPuntaje habilidad) {
        this.habilidad = habilidad;
    }
}
