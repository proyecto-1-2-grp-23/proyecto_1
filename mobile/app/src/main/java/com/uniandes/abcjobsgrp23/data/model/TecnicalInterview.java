package com.uniandes.abcjobsgrp23.data.model;


import java.util.ArrayList;

public class TecnicalInterview {

    private Proyecto proyecto;
    private Candidato candidato;
    private ArrayList<RecordTecnicalInterview> preguntas;
    private String observaciones;

    public TecnicalInterview(Proyecto proyecto, Candidato candidato, ArrayList<RecordTecnicalInterview> preguntas, String observaciones) {
        this.proyecto = proyecto;
        this.candidato = candidato;
        this.preguntas = preguntas;
        this.observaciones = observaciones;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public ArrayList<RecordTecnicalInterview> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<RecordTecnicalInterview> preguntas) {
        this.preguntas = preguntas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}