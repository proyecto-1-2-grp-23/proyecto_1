package com.uniandes.abcjobsgrp23.data.repository;

import com.uniandes.abcjobsgrp23.data.model.UserCredential;
import com.uniandes.abcjobsgrp23.data.service.UserCredentialServiceImpl;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class UserCredentialRespository {

    private UserCredentialServiceImpl userCredentialService;

    public UserCredentialRespository() {
        userCredentialService = new UserCredentialServiceImpl();
    }

    public void login(String correo, String contraseña, Callback<ResponseBody> callback) {
        UserCredential requestBody = new UserCredential(correo, contraseña);
        Call<ResponseBody> call = userCredentialService.login(requestBody);
        call.enqueue(callback);
    }
}
