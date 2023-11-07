package com.uniandes.abcjobsgrp23.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static final String BASE_URL = "http://127.0.0.1:5000/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static UserCredentialApi userCredential = retrofit.create(UserCredentialApi.class);
    public static CandidatoApi candidatoApi = retrofit.create(CandidatoApi.class);
    public static EntrevistaApi entrevistaApi = retrofit.create(EntrevistaApi.class);



}

