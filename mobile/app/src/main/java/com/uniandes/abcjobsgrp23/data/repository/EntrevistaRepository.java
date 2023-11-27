package com.uniandes.abcjobsgrp23.data.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntrevistaRepository {

    private final Application application;
    public EntrevistaRepository(Application application) {
        this.application = application;
    }

    public LiveData<Entrevista> getEntrevistaById(Integer entrevistaId) {
        MutableLiveData<Entrevista> entrevistasLiveData = new MutableLiveData<>();
        RetrofitBroker.getEntrevistaById( entrevistaId, new Callback<Entrevista>()  {
            @Override
            public void onResponse(@NonNull Call<Entrevista> call, Response<Entrevista> response) {
                if (response.isSuccessful()) {
                    entrevistasLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Entrevista> call, Throwable t) {
            }
        });
        return entrevistasLiveData;
    }

    public LiveData<List<Entrevista>> getAllEntrevistas() {
        MutableLiveData<List<Entrevista>> entrevistasLiveData = new MutableLiveData<>();
        RetrofitBroker.getAllEntrevistas(new Callback<List<Entrevista>>() {
            @Override
            public void onResponse(@NonNull Call<List<Entrevista>> call, Response<List<Entrevista>> response) {
                if (response.isSuccessful()) {
                    entrevistasLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Entrevista>> call, Throwable t) {
                // Manejar errores aquí
            }
        });
        return entrevistasLiveData;
    }

    public MutableLiveData<Entrevista> createEntrevista(Entrevista entrevista) {
        MutableLiveData<Entrevista> entrevistasLiveData = new MutableLiveData<>();
        RetrofitBroker.createEntrevista( entrevista, new Callback<Entrevista>()  {
            @Override
            public void onResponse(@NonNull Call<Entrevista> call, Response<Entrevista> response) {
                if (response.isSuccessful()) {
                    entrevistasLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Entrevista> call, Throwable t) {
            }
        });
        return entrevistasLiveData;
    }
}
