package com.uniandes.abcjobsgrp23.data.service;

import android.util.Log;
import android.widget.Toast;

import com.uniandes.abcjobsgrp23.data.model.ApiResponse;
import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.Empresa;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.model.Funcionario;
import com.uniandes.abcjobsgrp23.data.model.HabilidadPuntaje;
import com.uniandes.abcjobsgrp23.data.model.LoginResponse;
import com.uniandes.abcjobsgrp23.data.model.Pregunta;
import com.uniandes.abcjobsgrp23.data.model.Proyecto;
import com.uniandes.abcjobsgrp23.data.model.ResultadosDesempeno;
import com.uniandes.abcjobsgrp23.data.model.TecnicaPreguntas;
import com.uniandes.abcjobsgrp23.data.model.UserCredential;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitBroker {

    /**
     * Login Service
     * **/
    public static void  loginUser(UserCredential credential,  Callback<LoginResponse> callback ) {
        Call<LoginResponse> call = ApiClient.userCredential.loginUser(credential);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    /**
     * Funcionario Service
     * **/
    public static void getAllFunsionarios(Callback<List<Funcionario>> callback) {
        Call<List<Funcionario>> call = ApiClient.funcionarioApi.getAllFunsionarios();
        call.enqueue(new Callback<List<Funcionario>>() {
            @Override
            public void onResponse(Call<List<Funcionario>> call, Response<List<Funcionario>> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Funcionario>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    /**
     * Candidato Service
     * **/
    public static Candidato createCandidato(Candidato candidato) {
        Call<Candidato> call = ApiClient.candidatoApi.createCandidato(candidato);
        try {
            Response<Candidato> response = call.execute();
            if (response.isSuccessful()) {
                Log.e("SuccessCreationUser: ", response.toString());
                return response.body();
            } else {
                Log.e("ErrorCreationUser: ", response.toString());
                return null;
            }
        } catch (IOException e) {
            Log.e("ErrorCreationUser: ", Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }

    public static void getAllUsersCandidatos(Callback<List<Candidato>> callback) {
        Call<List<Candidato>> call = ApiClient.candidatoApi.getAllUsersCandidatos();
        call.enqueue(new Callback<List<Candidato>>() {
            @Override
            public void onResponse(Call<List<Candidato>> call, Response<List<Candidato>> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Candidato>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    /**
     * Empresa Service
     * **/
    public static void getAllEmpresas(Callback<List<Empresa>> callback) {
        Call<List<Empresa>> call = ApiClient.empresaApi.getAllEmpresa();
        call.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    /**
     * Entrevista Service
     * **/
    public static void createEntrevista(Entrevista entrevista, Callback<Entrevista> callback ) {
        Call<Entrevista> call = ApiClient.entrevistaApi.createEntrevista(entrevista);
        call.enqueue(new Callback<Entrevista>() {
            @Override
            public void onResponse(Call<Entrevista> call, Response<Entrevista> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<Entrevista> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public static void getAllEntrevistas(Callback<List<Entrevista>> callback) {
        Call<List<Entrevista>> call = ApiClient.entrevistaApi.getAllEntrevistas();
        call.enqueue(new Callback<List<Entrevista>>() {
            @Override
            public void onResponse(Call<List<Entrevista>> call, Response<List<Entrevista>> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Entrevista>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public static void getEntrevistaById(Integer entrevistaId, Callback<Entrevista> callback) {
        Call<Entrevista> call = ApiClient.entrevistaApi.getEntrevistaById(entrevistaId);
        call.enqueue(new Callback<Entrevista>() {
            @Override
            public void onResponse(Call<Entrevista> call, Response<Entrevista> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<Entrevista> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    /**
     * Proyecto Service
     * **/
    public static void getAllProyectos(Callback<List<Proyecto>> callback) {
        Call<List<Proyecto>> call = ApiClient.proyectoApi.getAllProyectos();
        call.enqueue(new Callback<List<Proyecto>>() {
            @Override
            public void onResponse(Call<List<Proyecto>> call, Response<List<Proyecto>> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Proyecto>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    /**
     * Preguntas Service
     * **/
    public static void getAllPreguntasTecnicas(Callback<List<TecnicaPreguntas>> callback) {
        Call<List<TecnicaPreguntas>> call = ApiClient.preguntaApi.getAllPreguntasTecnicas();
        call.enqueue(new Callback<List<TecnicaPreguntas>>() {
            @Override
            public void onResponse(Call<List<TecnicaPreguntas>> call, Response<List<TecnicaPreguntas>> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<TecnicaPreguntas>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

    public static void getAllPreguntasTecnicasbyIdProyecto(int idProyecto, Callback<List<TecnicaPreguntas>> callback) {
        Call<List<TecnicaPreguntas>> call = ApiClient.preguntaApi.getAllPreguntasTecnicasbyIdProyecto(idProyecto);
        call.enqueue(new Callback<List<TecnicaPreguntas>>() {
            @Override
            public void onResponse(Call<List<TecnicaPreguntas>> call, Response<List<TecnicaPreguntas>> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<TecnicaPreguntas>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }


    /**
     * Pruebas Desempeño
     * **/
    public static void enviarPruebaDesempeno(int candidatoId, int idPrueba, int idEmpresa, String nombreHabilidad, int puntaje, Callback<ApiResponse> callback) {
        ResultadosDesempeno resultadosDesempeno = new ResultadosDesempeno( idPrueba,  candidatoId,  idEmpresa, nombreHabilidad, puntaje);

        Call<ApiResponse> call = ApiClient.pruebaDesempenoApi.enviarPruebaDesempeno(resultadosDesempeno);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });
    }

}

