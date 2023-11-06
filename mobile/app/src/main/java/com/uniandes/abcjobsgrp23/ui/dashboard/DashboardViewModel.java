package com.uniandes.abcjobsgrp23.ui.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> userType;

    public DashboardViewModel() {
        userType = new MutableLiveData<>();
        userType.setValue("");
    }

}