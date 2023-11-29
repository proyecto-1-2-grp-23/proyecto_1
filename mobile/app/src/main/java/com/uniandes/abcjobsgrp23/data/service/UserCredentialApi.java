package com.uniandes.abcjobsgrp23.data.service;
import com.uniandes.abcjobsgrp23.data.model.LoginResponse;
import com.uniandes.abcjobsgrp23.data.model.UserCredential;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserCredentialApi {

    @POST("users/login")
    Call<LoginResponse> loginUser(@Body UserCredential userCredential);
}