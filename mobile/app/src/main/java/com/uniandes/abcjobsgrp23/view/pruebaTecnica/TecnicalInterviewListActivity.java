package com.uniandes.abcjobsgrp23.view.pruebaTecnica;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.viewmodel.TecnicalInterviewViewModel;

import java.util.ArrayList;

public class TecnicalInterviewListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecordTecnicalInterviewAdapter adapter;
    private TecnicalInterviewViewModel tecnicalInterviewViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_preguntas_respuestas);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa el ViewModel
        tecnicalInterviewViewModel = new ViewModelProvider(this).get(TecnicalInterviewViewModel.class);

        // Inicializa el adaptador (pasa una lista vacía inicialmente)
        adapter = new RecordTecnicalInterviewAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Observa los cambios en la lista de entrevistas
        tecnicalInterviewViewModel.getAllTecnicalInterviews().observe(this, entrevistas -> {
            // Actualiza el adaptador con la nueva lista de entrevistas
            adapter.setTecnicalInterview(entrevistas);
        });
    }

}

