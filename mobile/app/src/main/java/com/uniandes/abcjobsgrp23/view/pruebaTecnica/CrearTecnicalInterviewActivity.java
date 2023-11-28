package com.uniandes.abcjobsgrp23.view.pruebaTecnica;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.uniandes.abcjobsgrp23.data.model.TecnicaPreguntas;
import com.uniandes.abcjobsgrp23.data.model.TecnicalInterview;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;
import com.uniandes.abcjobsgrp23.viewmodel.TecnicalInterviewViewModel;

import java.util.ArrayList;
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

        // Obtener preguntas
        cargarPreguntas();

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

                // Crear una lista de strings con la información deseada
                List<String> candidatoStrings = new ArrayList<>();
                for (Candidato candidato : response.body()) {
                    String candidatoInfo = candidato.getId() + "- " + candidato.getNombreCompleto();  // Ajusta esto según tus atributos
                    candidatoStrings.add(candidatoInfo);
                }
                // Crear un ArrayAdapter con la lista de strings
                ArrayAdapter<String> candidatosAdapter = new ArrayAdapter<>(CrearTecnicalInterviewActivity.this, android.R.layout.simple_spinner_item, candidatoStrings);
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

                // Crear una lista de strings con la información deseada
                List<String> proyectoStrings = new ArrayList<>();
                for (Proyecto proyecto : response.body()) {
                    String proyectoInfo = proyecto.getId() + ") " + proyecto.getNombre() + " [" + proyecto.getStartDate() + " - " + proyecto.getFinishDate() + "] ";  // Ajusta esto según tus atributos
                    proyectoStrings.add(proyectoInfo);
                }

                ArrayAdapter<String> proyectosAdapter = new ArrayAdapter<>(CrearTecnicalInterviewActivity.this, android.R.layout.simple_spinner_item, proyectoStrings);
                proyectosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProyectos.setAdapter(proyectosAdapter);
            }

            @Override
            public void onFailure(Call<List<Proyecto>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CrearTecnicalInterviewActivity.this, "Fallo al obtener proyectos", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerProyectos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener la cadena seleccionada en lugar de un objeto Proyecto
                String proyectoSeleccionadoString = (String) parentView.getItemAtPosition(position);

                if (proyectoSeleccionadoString != null) {
                    // Obtener el ID del proyecto seleccionado a partir de la cadena
                    String[] parts = proyectoSeleccionadoString.split("\\) ");
                    int idProyecto = Integer.parseInt(parts[0]);

                    // Llamar al método para cargar las preguntas basadas en el proyecto seleccionado
                    cargarPreguntasPorProyecto(idProyecto);
                }
            }

            private void cargarPreguntasPorProyecto(int idProyecto) {
                // Realizar la llamada para obtener preguntas basadas en el proyecto seleccionado
                RetrofitBroker.getAllPreguntasTecnicasbyIdProyecto(idProyecto, new Callback<List<TecnicaPreguntas>>() {
                    @Override
                    public void onResponse(Call<List<TecnicaPreguntas>> call, Response<List<TecnicaPreguntas>> response) {
                        // Actualizar el adaptador del RecyclerView con las preguntas
                        PreguntasAdapter preguntasAdapter = (PreguntasAdapter) recyclerViewPreguntas.getAdapter();
                        preguntasAdapter.setPreguntas(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<TecnicaPreguntas>> call, Throwable t) {
                        // Manejar fallo
                        Toast.makeText(CrearTecnicalInterviewActivity.this, "Fallo al obtener preguntas", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No se necesita realizar ninguna acción aquí
            }
        });

    }


    private void cargarPreguntas() {
        RetrofitBroker.getAllPreguntasTecnicas(new Callback<List<TecnicaPreguntas>>() {
            @Override
            public void onResponse(Call<List<TecnicaPreguntas>> call, Response<List<TecnicaPreguntas>> response) {
                // Actualizar el adaptador del RecyclerView con las preguntas
                PreguntasAdapter preguntasAdapter = (PreguntasAdapter) recyclerViewPreguntas.getAdapter();
                preguntasAdapter.setPreguntas(response.body());
            }

            @Override
            public void onFailure(Call<List<TecnicaPreguntas>> call, Throwable t) {
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
