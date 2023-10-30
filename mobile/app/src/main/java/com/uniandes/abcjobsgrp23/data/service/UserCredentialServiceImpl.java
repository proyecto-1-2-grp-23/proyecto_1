package com.uniandes.abcjobsgrp23.data.service;

import com.uniandes.abcjobsgrp23.data.model.UserCredential;
import com.uniandes.abcjobsgrp23.data.serviceImpl.UserCredencialService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserCredentialServiceImpl {

    private UserCredencialService userCredencialService;

    public UserCredentialServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userCredencialService = retrofit.create(UserCredencialService.class);
    }

    public Call<ResponseBody> login(UserCredential requestBody) {
        return userCredencialService.login(requestBody);
    }

}
