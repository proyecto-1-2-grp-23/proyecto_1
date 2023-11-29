package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.Funcionario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FuncionarioApi {

    @GET("users/funcionarios/1")
    Call<List<Funcionario>> getAllFunsionarios();
}
