package com.uniandes.abcjobsgrp23.data.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // Registra la solicitud
        System.out.println("Request: " + request.toString());
        okhttp3.Response response = chain.proceed(request);
        // Registra la respuesta
        System.out.println("Response: " + response.toString());
        return response;
    }
}
