package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.Entrevista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EntrevistaApi {
    @GET("users/entrevistas/{entrevistaId}")
    Call<Entrevista> getEntrevistaById(@Path("entrevistaId") Integer entrevistaId);

    @GET("users/entrevistas")
    Call<List<Entrevista>> getAllEntrevistas();

    @POST("users/entrevistas")
    Call<Entrevista> createEntrevista(@Body Entrevista entrevista);
}
