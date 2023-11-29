package com.uniandes.abcjobsgrp23.data.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.model.LoginResponse;
import com.uniandes.abcjobsgrp23.data.model.UserCredential;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCredentialRespository {
    private Application application;

    public UserCredentialRespository(Application application) {
        this.application = application;
    }

//    public LoginResponse loginUser(UserCredential userCredential, new Callback<LoginResponse>()) {
//        return RetrofitBroker.loginUser(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<LoginResponse> call, Response<LoginResponse> response) {
//                if (response.isSuccessful()) {
//                    new UserCredential(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<Entrevista>> call, Throwable t) {
//                // Manejar errores aquí
//            }
//        });
//        return null ;
//    }

}
