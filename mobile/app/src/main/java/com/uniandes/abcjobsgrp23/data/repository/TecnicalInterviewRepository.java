package com.uniandes.abcjobsgrp23.data.repository;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.Proyecto;
import com.uniandes.abcjobsgrp23.data.model.RecordTecnicalInterview;
import com.uniandes.abcjobsgrp23.data.model.TecnicalInterview;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TecnicalInterviewRepository {

    private final Application application;
    public TecnicalInterviewRepository(Application application) {
        this.application = application;
    }

    public LiveData<List<TecnicalInterview>> getAllTecnicalInterviews() {
        MutableLiveData<List<TecnicalInterview>> tecnicalInterviewLiveData = new MutableLiveData<>();

        // Primer registro de prueba
        Proyecto proyecto1 = new Proyecto(
                "HTML, CSS, JavaScript",
                "2023-11-27T13:50:57.748667",
                "Crear una aplicación Web",
                "2023-11-16",
                "Resolución de problemas",
                2,
                1,
                "Aplicación de Web",
                "Gerente de proyecto.",
                "2023-11-16"
        );
        Candidato candidato1 = new Candidato("Candidato 1", 28, "123-456-7890", "candidato1@example.com", "País 1", "Ciudad 1", "Español, Inglés", "Rasgos 1", "Password 1");
        ArrayList<RecordTecnicalInterview> preguntas1 = new ArrayList<>();
        preguntas1.add(new RecordTecnicalInterview("Pregunta 1", "Calificación 1"));
        preguntas1.add(new RecordTecnicalInterview("Pregunta 2", "Calificación 2"));
        preguntas1.add(new RecordTecnicalInterview("Pregunta 3", "Calificación 3"));
        TecnicalInterview entrevista1 = new TecnicalInterview(proyecto1, candidato1, preguntas1, "Observaciones de la Entrevista 1");

        // Segundo registro de prueba
        Proyecto proyecto2 = new Proyecto(
                "Java, Spring Boot",
                "2023-11-28T09:30:45.123456",
                "Desarrollar un sistema de gestión",
                "2023-12-10",
                "Trabajo en equipo",
                3,
                1,
                "Sistema de Gestión",
                "Desarrollador senior",
                "2023-12-01"
        );
        Candidato candidato2 = new Candidato("Candidato 2", 30, "987-654-3210", "candidato2@example.com", "País 2", "Ciudad 2", "Español, Francés", "Rasgos 2", "Password 2");
        ArrayList<RecordTecnicalInterview> preguntas2 = new ArrayList<>();
        preguntas2.add(new RecordTecnicalInterview("Pregunta A", "Calificación A"));
        preguntas2.add(new RecordTecnicalInterview("Pregunta B", "Calificación B"));
        preguntas2.add(new RecordTecnicalInterview("Pregunta C", "Calificación C"));
        TecnicalInterview entrevista2 = new TecnicalInterview(proyecto2, candidato2, preguntas2, "Observaciones de la Entrevista 2");

        // Tercer registro de prueba
        Proyecto proyecto3 = new Proyecto(
                "Python, Django",
                "2023-11-29T14:20:30.987654",
                "Crear una plataforma de comercio electrónico",
                "2023-12-20",
                "Comunicación efectiva",
                4,
                2,
                "Plataforma de E-commerce",
                "Product manager",
                "2023-12-05"
        );
        Candidato candidato3 = new Candidato("Candidato 3", 25, "555-123-4567", "candidato3@example.com", "País 3", "Ciudad 3", "Español, Alemán", "Rasgos 3", "Password 3");
        ArrayList<RecordTecnicalInterview> preguntas3 = new ArrayList<>();
        preguntas3.add(new RecordTecnicalInterview("Pregunta X", "Calificación X"));
        preguntas3.add(new RecordTecnicalInterview("Pregunta Y", "Calificación Y"));
        preguntas3.add(new RecordTecnicalInterview("Pregunta Z", "Calificación Z"));
        TecnicalInterview entrevista3 = new TecnicalInterview(proyecto3, candidato3, preguntas3, "Observaciones de la Entrevista 3");

        // Agregar los registros de prueba a la lista de entrevistas
        List<TecnicalInterview> entrevistasDePrueba = new ArrayList<>();
        entrevistasDePrueba.add(entrevista1);
        entrevistasDePrueba.add(entrevista2);
        entrevistasDePrueba.add(entrevista3);

        // Configurar la LiveData con los registros de prueba
        tecnicalInterviewLiveData.setValue(entrevistasDePrueba);

        //        RetrofitBroker.getAllTecnicalInterviews(new Callback<List<TecnicalInterview>>() {
        //            @Override
        //            public void onResponse(@NonNull Call<List<TecnicalInterview>> call, Response<List<TecnicalInterview>> response) {
        //                if (response.isSuccessful()) {
        //                    tecnicalInterviewLiveData.setValue(response.body());
        //                }
        //            }
        //
        //            @Override
        //            public void onFailure(@NonNull Call<List<TecnicalInterview>> call, Throwable t) {
        //                // Manejar errores aquí
        //            }
        //        });

        return tecnicalInterviewLiveData;
    }

}

