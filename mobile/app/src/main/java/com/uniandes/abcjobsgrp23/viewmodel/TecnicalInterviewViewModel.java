package com.uniandes.abcjobsgrp23.viewmodel;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.uniandes.abcjobsgrp23.data.model.TecnicalInterview;
import com.uniandes.abcjobsgrp23.data.repository.TecnicalInterviewRepository;

import java.util.List;

public class TecnicalInterviewViewModel extends AndroidViewModel {

    private final LiveData<List<TecnicalInterview>> tecnicalInterviewList;
    private final TecnicalInterviewRepository tecnicalInterviewRepository;

    public TecnicalInterviewViewModel(Application application) {
        super(application);
        tecnicalInterviewRepository = new TecnicalInterviewRepository(application);
        tecnicalInterviewList = tecnicalInterviewRepository.getAllTecnicalInterviews();
    }

    public LiveData<List<TecnicalInterview>> getAllTecnicalInterviews() {
        return tecnicalInterviewList;
    }
}

