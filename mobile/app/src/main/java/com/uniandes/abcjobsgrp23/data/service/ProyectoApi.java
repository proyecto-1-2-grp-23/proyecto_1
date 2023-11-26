package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.Proyecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProyectoApi {

    // http://127.0.0.1:3002/projects/listar-projects
    @GET("/projects/listar-projects")
    Call<List<Proyecto>> getAllProyectos();

}
