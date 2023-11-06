package com.uniandes.abcjobsgrp23.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    // Atributo para almacenar el tipo de usuario
    private final MutableLiveData<String> userType;

    public HomeViewModel() {
        userType = new MutableLiveData<>();
        userType.setValue("");
    }

}