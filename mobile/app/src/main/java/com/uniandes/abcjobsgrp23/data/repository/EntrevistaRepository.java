package com.uniandes.abcjobsgrp23.data.repository;

import android.app.Application;

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

    public Entrevista getEntrevistaById(Integer entrevistaId) {
        return RetrofitBroker.getEntrevistaById(entrevistaId);
    }
    public LiveData<List<Entrevista>> getAllEntrevistas() {
        MutableLiveData<List<Entrevista>> entrevistasLiveData = new MutableLiveData<>();
        RetrofitBroker.getAllEntrevistas(new Callback<List<Entrevista>>() {
            @Override
            public void onResponse(Call<List<Entrevista>> call, Response<List<Entrevista>> response) {
                if (response.isSuccessful()) {
                    entrevistasLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Entrevista>> call, Throwable t) {
                // Manejar errores aquí
            }
        });
        return entrevistasLiveData;
    }

    public Entrevista createEntrevista(Entrevista entrevista) {
        return RetrofitBroker.createEntrevista(entrevista);
    }
}
