package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.Pregunta;
import com.uniandes.abcjobsgrp23.data.model.Proyecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PreguntaApi {


    // http://127.0.0.1:3001/pruebas/preguntas
    @GET("/projects/listar-projects")
    Call<List<Pregunta>> getAllPreguntas();

}
