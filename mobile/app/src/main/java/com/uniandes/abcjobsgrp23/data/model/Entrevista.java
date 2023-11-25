package com.uniandes.abcjobsgrp23.data.model;

import java.util.Objects;

public class Entrevista {

    private Empresa empresa;
    private Candidato candidato;
    private Funcionario funcionario;
    private String fecha;
    private String lugar;
    private Integer idCandidato;
    private Integer idEmpresa;
    private Integer idFuncionario;

    // Constructor para crear entrevistas
    public Entrevista(Integer idFuncionario, Empresa empresa, Candidato candidato, String fecha, String lugar) {
        this.idFuncionario = idFuncionario;
        this.empresa = empresa;
        this.candidato = candidato;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    // Constructor para consultar entrevistas
    public Entrevista(Empresa empresa, Candidato candidato, String fecha,
                      Integer idCandidato, Integer idEmpresa, Integer idFuncionario, String lugar) {
        this.empresa = empresa;
        this.candidato = candidato;
        this.fecha = fecha;
        this.idCandidato = idCandidato;
        this.idEmpresa = idEmpresa;
        this.idFuncionario = idFuncionario;
        this.lugar = lugar;
    }

    // Constructor para consultar entrevistas
    public Entrevista(Empresa empresa, Candidato candidato, Funcionario funcionario, String fecha,
                      Integer idCandidato, Integer idEmpresa, Integer idFuncionario, String lugar) {
        this.empresa = empresa;
        this.candidato = candidato;
        this.funcionario = funcionario;
        this.fecha = fecha;
        this.idCandidato = idCandidato;
        this.idEmpresa = idEmpresa;
        this.idFuncionario = idFuncionario;
        this.lugar = lugar;
    }
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrevista entrevista = (Entrevista) o;
        return Objects.equals(empresa, entrevista.empresa) &&
                Objects.equals(candidato, entrevista.candidato) &&
                Objects.equals(funcionario, entrevista.funcionario) &&
                Objects.equals(fecha, entrevista.fecha) &&
                Objects.equals(lugar, entrevista.lugar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresa, candidato, funcionario, fecha, lugar);
    }
}
