package com.uniandes.abcjobsgrp23.data.service;

import android.util.Log;
import android.widget.Toast;

import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;
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
    public static UserCredential loginUser(UserCredential userCredential) {
        Call<UserCredential> call = ApiClient.userCredential.loginUser(userCredential);
        try {
            Response<UserCredential> response = call.execute();
            if (response.isSuccessful()) {
                Log.e("SuccessLoginUser: ", response.toString());
                return response.body();
            } else {
                Log.e("ErrorLoginUser: ", response.toString());
                return null;
            }
        } catch (IOException e) {
            Log.e("ErrorLoginUser: ", Objects.requireNonNull(e.getMessage()));
            return null;
        }
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
    public static List<Candidato> getAllUsersCandidatos() {
        Call<List<Candidato>> call = ApiClient.candidatoApi.getAllUsersCandidatos();
        try {
            Response<List<Candidato>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
            return new ArrayList<>();
        }
    }

    /**
     * Entrevista Service
     * **/
    public static Entrevista createEntrevista(Entrevista entrevista) {
        Call<Entrevista> call = ApiClient.entrevistaApi.createEntrevista(entrevista);
        try {
            Response<Entrevista> response = call.execute();
            if (response.isSuccessful()) {
                Log.e("SuccessCreationEntrevista: ", response.toString());
                return response.body();
            } else {
                Log.e("ErrorCreationEntrevista: ", response.toString());
                return null;
            }
        } catch (IOException e) {
            Log.e("ErrorCreationEntrevista: ", Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }

    public static void getAllEntrevistas(Callback<List<Entrevista>> callback) {
        Call<List<Entrevista>> call = ApiClient.entrevistaApi.getAllEntrevistas();
        call.enqueue(new Callback<List<Entrevista>>() {
            @Override
            public void onResponse(Call<List<Entrevista>> call, Response<List<Entrevista>> response) {

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                // Añade el interceptor personalizado para el registro
                httpClient.addInterceptor(new LoggingInterceptor());

                if (response.isSuccessful()) {
                    if (callback != null) {
                        callback.onResponse(call, response);
                    }
                } else {
                    // Manejar la respuesta de error aquí
                    if (callback != null) {
                        callback.onFailure(call, new Exception("Error en la llamada a la API"));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Entrevista>> call, Throwable t) {
                // Manejar errores de red aquí
                if (callback != null) {
                    callback.onFailure(call, t);
                }
            }
        });

    }
    public static Entrevista getEntrevistaById(Integer entrevistaId) {
        Call<Entrevista> call = ApiClient.entrevistaApi.getEntrevistaById(entrevistaId);
        try {
            Response<Entrevista> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }
}

