package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.TecnicaPreguntas;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PreguntaApi {

    // http://127.0.0.1:3001/pruebas/preguntas
    @GET("/pruebas/preguntas")
    Call<List<TecnicaPreguntas>> getAllPreguntasTecnicas();

    // http://127.0.0.1:3001/pruebas/preguntas/proyectos/
    @GET("pruebas/preguntas/proyectos/{proyectoId}")
    Call<List<TecnicaPreguntas>> getAllPreguntasTecnicasbyIdProyecto(@Path("proyectoId") Integer proyectoId);


}
