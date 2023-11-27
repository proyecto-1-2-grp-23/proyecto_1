package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.ApiResponse;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.model.ResultadosDesempeno;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface pruebaDesempenoApi {


    @POST("pruebas/evaluacion_desempeño")
    Call<ApiResponse> enviarPruebaDesempeno(@Body ResultadosDesempeno resultadosDesempeno);
}
