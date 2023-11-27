package com.uniandes.abcjobsgrp23.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {

    // Define las URLs base para cada servicio
    private static final String BASE_URL_USER_CREDENTIAL = "http://10.0.2.2:3000/"; // http://127.0.0.1:3000/users/login
    private static final String BASE_URL_CANDIDATO = "http://10.0.2.2:3000/"; // http://127.0.0.1:3000/users/candidatos
    private static final String BASE_URL_ENTREVISTA = "http://10.0.2.2:3000/"; // http://127.0.0.1:3000/users/entrevistas
    private static final String BASE_URL_PROYECTO = "http://10.0.2.2:3002/"; // http://127.0.0.1:3002/projects/listar-projects
    private static final String BASE_URL_PREGUNTA = "http://10.0.2.2:3001/"; // http://127.0.0.1:3001/pruebas/preguntas
    private static final String BASE_URL_PRUEBA_DESEMPENO = "http://34.110.178.56/"; // http://127.0.0.1:3001/pruebas/preguntas
    private static final String BASE_URL_INGRESS = "http://34.110.178.56/"; // http://127.0.0.1:3001/pruebas/preguntas

    // Crea instancias diferentes de Retrofit para cada servicio
    private static final Retrofit retrofitUserCredential = new Retrofit.Builder()
            .baseUrl(BASE_URL_INGRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Retrofit retrofitCandidato = new Retrofit.Builder()
            .baseUrl(BASE_URL_INGRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Retrofit retrofitEntrevista = new Retrofit.Builder()
            .baseUrl(BASE_URL_INGRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Retrofit retrofitProyecto = new Retrofit.Builder()
            .baseUrl(BASE_URL_INGRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Retrofit retrofitPregunta = new Retrofit.Builder()
            .baseUrl(BASE_URL_INGRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Retrofit retrofitPruebaDesempeno = new Retrofit.Builder()
            .baseUrl(BASE_URL_INGRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // Crea instancias diferentes de los servicios utilizando las instancias de Retrofit respectivas
    public static UserCredentialApi userCredential = retrofitUserCredential.create(UserCredentialApi.class);
    public static CandidatoApi candidatoApi = retrofitCandidato.create(CandidatoApi.class);
    public static EntrevistaApi entrevistaApi = retrofitEntrevista.create(EntrevistaApi.class);
    public static ProyectoApi proyectoApi = retrofitProyecto.create(ProyectoApi.class);
    public static PreguntaApi preguntaApi = retrofitPregunta.create(PreguntaApi.class);
    public static pruebaDesempenoApi pruebaDesempenoApi = retrofitPruebaDesempeno.create(pruebaDesempenoApi.class);

}

