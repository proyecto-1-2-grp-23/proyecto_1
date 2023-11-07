package com.uniandes.abcjobsgrp23.data.repository;

import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;

import java.util.List;

public class EntrevistaRepository {

    public Entrevista getEntrevistaById(Integer entrevistaId) {
        return RetrofitBroker.getEntrevistaById(entrevistaId);
    }
    public List<Entrevista> getAllEntrevistas() {
        return RetrofitBroker.getAllEntrevistas();
    }
    public Entrevista createEntrevista(Entrevista entrevista) {
        return RetrofitBroker.createEntrevista(entrevista);
    }
}
