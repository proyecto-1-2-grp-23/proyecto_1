package com.uniandes.abcjobsgrp23.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.repository.EntrevistaRepository;

import java.util.List;

import retrofit2.Callback;

public class EntrevistaViewModel extends AndroidViewModel {

    private final LiveData<List<Entrevista>> entrevistasList;
    private final EntrevistaRepository entrevistaRepository;

    public EntrevistaViewModel(Application application) {
        super(application);
        entrevistaRepository = new EntrevistaRepository(application);
        entrevistasList = entrevistaRepository.getAllEntrevistas();
    }

    public LiveData<List<Entrevista>> getAllEntrevistas() {
        return entrevistasList;
    }
}
