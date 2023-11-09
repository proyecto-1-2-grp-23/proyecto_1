package com.uniandes.abcjobsgrp23.data.service;


import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.UserCredential;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CandidatoApi {
    @GET("users/candidatos")
    Call<List<Candidato>> getAllUsersCandidatos();

    @POST("users")
    Call<Candidato> createCandidato(@Body Candidato candidato);

}
