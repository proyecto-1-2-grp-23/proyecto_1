package com.uniandes.abcjobsgrp23.data.repository;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidatoRepository {

    private final Application application;
    public CandidatoRepository(Application application) {
        this.application = application;
    }

    public LiveData<List<Candidato>> getAllUsersCandidatos() {
        MutableLiveData<List<Candidato>> candidatosLiveData = new MutableLiveData<>();
        RetrofitBroker.getAllUsersCandidatos(new Callback<List<Candidato>>() {
            @Override
            public void onResponse(@NonNull Call<List<Candidato>> call, Response<List<Candidato>> response) {
                if (response.isSuccessful()) {
                    candidatosLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Candidato>> call, Throwable t) {
                // Manejar errores aquí
            }
        });
        return candidatosLiveData;
    }

    public Candidato createCandidato(Candidato candidato) {
        return RetrofitBroker.createCandidato(candidato);
    }

}