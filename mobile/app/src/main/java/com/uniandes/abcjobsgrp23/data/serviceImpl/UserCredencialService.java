package com.uniandes.abcjobsgrp23.data.serviceImpl;
import com.uniandes.abcjobsgrp23.data.model.UserCredential;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface UserCredencialService {
    @POST("users/login")
    Call<ResponseBody> login(@Body UserCredential requestBody);
}
