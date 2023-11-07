package com.uniandes.abcjobsgrp23.data.repository;


import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.UserCredential;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;

import java.util.List;

public class CandidatoRepository {

    public List<Candidato> getAllUsersCandidatos() {
        return RetrofitBroker.getAllUsersCandidatos();
    }

    public Candidato createCandidato(Candidato candidato) {
        return RetrofitBroker.createCandidato(candidato);
    }

}