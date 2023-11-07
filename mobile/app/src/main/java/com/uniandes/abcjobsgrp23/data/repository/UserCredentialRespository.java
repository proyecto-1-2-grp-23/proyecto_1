package com.uniandes.abcjobsgrp23.data.repository;

import android.app.Application;

import com.uniandes.abcjobsgrp23.data.model.UserCredential;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;

import java.util.List;

public class UserCredentialRespository {
    private Application application;

    public UserCredentialRespository(Application application) {
        this.application = application;
    }

    public UserCredential loginUser(UserCredential userCredential) {
        return RetrofitBroker.loginUser(userCredential);
    }

}
