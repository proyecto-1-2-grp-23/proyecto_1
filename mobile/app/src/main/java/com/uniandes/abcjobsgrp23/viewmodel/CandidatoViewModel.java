package com.uniandes.abcjobsgrp23.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.repository.CandidatoRepository;
import java.util.List;

public class CandidatoViewModel extends AndroidViewModel {

    private final CandidatoRepository candidatoRepository;
    private final LiveData<List<Candidato>> candidatosList;

    public CandidatoViewModel(Application application) {
        super(application);
        candidatoRepository = new CandidatoRepository(application);
        candidatosList = candidatoRepository.getAllUsersCandidatos();
    }

    public LiveData<List<Candidato>> getAllUsersCandidatos() {
        return candidatosList;
    }
}
