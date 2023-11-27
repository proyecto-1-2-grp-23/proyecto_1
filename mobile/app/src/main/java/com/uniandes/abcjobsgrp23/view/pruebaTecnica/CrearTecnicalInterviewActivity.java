package com.uniandes.abcjobsgrp23.view.pruebaTecnica;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.Pregunta;
import com.uniandes.abcjobsgrp23.data.model.Proyecto;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;
import com.uniandes.abcjobsgrp23.viewmodel.TecnicalInterviewViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearTecnicalInterviewActivity extends AppCompatActivity {

    private Spinner spinnerCandidatos;
    private Spinner spinnerProyectos;
    private RecyclerView recyclerViewPreguntas;
    private Button btnCrearTecnicalInterview;
    private Button btnCancelarTecnicalInterview;

    private TecnicalInterviewViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tecnical_interview);

        // Inicializar vistas
        spinnerCandidatos = findViewById(R.id.spinnerCandidatos);
        spinnerProyectos = findViewById(R.id.spinnerProyectos);
        recyclerViewPreguntas = findViewById(R.id.recyclerViewPreguntas);
        btnCrearTecnicalInterview = findViewById(R.id.btnCrearTecnicalInterview);
        btnCancelarTecnicalInterview = findViewById(R.id.btnCancelarTecnicalInterview);

        // Configurar RecyclerView para Preguntas
        recyclerViewPreguntas.setLayoutManager(new LinearLayoutManager(this));
        PreguntasAdapter preguntasAdapter = new PreguntasAdapter();
        recyclerViewPreguntas.setAdapter(preguntasAdapter);

        // Configurar Spinners
        setupSpinners();

        // Configurar ViewModel
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(TecnicalInterviewViewModel.class);

        // Configurar botones
        btnCrearTecnicalInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar lógica para guardar la entrevista técnica
                guardarEntrevista();
            }
        });

        btnCancelarTecnicalInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar lógica para cancelar la creación de entrevista técnica
                finish();
            }
        });
    }

    private void setupSpinners() {
        // Configurar el adapter para el Spinner de Candidatos
        RetrofitBroker.getAllUsersCandidatos(new retrofit2.Callback<List<Candidato>>() {
            @Override
            public void onResponse(Call<List<Candidato>> call, Response<List<Candidato>> response) {
                ArrayAdapter<Candidato> candidatosAdapter = new ArrayAdapter<>(CrearTecnicalInterviewActivity.this, android.R.layout.simple_spinner_item, response.body());
                candidatosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCandidatos.setAdapter(candidatosAdapter);
            }

            @Override
            public void onFailure(Call<List<Candidato>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CrearTecnicalInterviewActivity.this, "Fallo al obtener candidatos", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el adapter para el Spinner de Proyectos
        RetrofitBroker.getAllProyectos(new Callback<List<Proyecto>>() {
            @Override
            public void onResponse(Call<List<Proyecto>> call, Response<List<Proyecto>> response) {
                ArrayAdapter<Proyecto> proyectosAdapter = new ArrayAdapter<>(CrearTecnicalInterviewActivity.this, android.R.layout.simple_spinner_item, response.body());
                proyectosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProyectos.setAdapter(proyectosAdapter);
            }

            @Override
            public void onFailure(Call<List<Proyecto>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CrearTecnicalInterviewActivity.this, "Fallo al obtener proyectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarPreguntas() {
        RetrofitBroker.getAllPreguntas(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                // Actualizar el adaptador del RecyclerView con las preguntas
                PreguntasAdapter preguntasAdapter = (PreguntasAdapter) recyclerViewPreguntas.getAdapter();
                preguntasAdapter.setPreguntas(response.body());
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CrearTecnicalInterviewActivity.this, "Fallo al obtener preguntas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarEntrevista() {
        // Agregar lógica para guardar la entrevista técnica
        Toast.makeText(this, "Entrevista técnica guardada", Toast.LENGTH_SHORT).show();
        finish();
    }
}
