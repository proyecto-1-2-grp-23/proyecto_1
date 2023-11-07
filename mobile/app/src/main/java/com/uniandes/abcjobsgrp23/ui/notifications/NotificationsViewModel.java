package com.uniandes.abcjobsgrp23.ui.notifications;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> userType;

    public NotificationsViewModel() {
        userType = new MutableLiveData<>();
        userType.setValue("");
    }


}