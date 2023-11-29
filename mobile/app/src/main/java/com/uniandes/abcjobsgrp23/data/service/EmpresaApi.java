package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.Empresa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmpresaApi {

    @GET("/users/empresa")
    Call<List<Empresa>> getAllEmpresa();

}
