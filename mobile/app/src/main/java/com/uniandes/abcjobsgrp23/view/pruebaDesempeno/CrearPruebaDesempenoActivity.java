package com.uniandes.abcjobsgrp23.view.pruebaDesempeno;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.HabilidadPuntaje;
import com.uniandes.abcjobsgrp23.data.model.ResultadosDesempeno;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;
import com.uniandes.abcjobsgrp23.view.candidato.CustomCandidatoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CrearPruebaDesempenoActivity extends AppCompatActivity {

    private PruebaDesempenoViewModel viewModel;
    private Spinner spinnerCandidatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evaluacion_desempeno);  // Actualiza aquí el nombre del layout

        viewModel = new ViewModelProvider(this).get(PruebaDesempenoViewModel.class);

        // Inicializar vistas
        spinnerCandidatos = findViewById(R.id.spinnerCandidatosDS);
        LinearLayout containerSkills = findViewById(R.id.containerSkills);
        Button btnAddSkill = findViewById(R.id.btnAddSkill);
        Button btnGuardar = findViewById(R.id.btnCrearPruebaDesempeno);

        // Configurar Spinners
        setupSpinners();

        btnAddSkill.setOnClickListener(v -> agregarHabilidad());
        btnGuardar.setOnClickListener(v -> guardarPruebaDesempeno());
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
                ArrayAdapter<String> candidatosAdapter = new ArrayAdapter<>(CrearPruebaDesempenoActivity.this, android.R.layout.simple_spinner_item, candidatoStrings);
                candidatosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Configurar el Spinner con el ArrayAdapter modificado
                spinnerCandidatos.setAdapter(candidatosAdapter);
            }

            @Override
            public void onFailure(Call<List<Candidato>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CrearPruebaDesempenoActivity.this, "Fallo al obtener candidatos", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void guardarPruebaDesempeno() {

        int candidatoId = 1;
        ResultadosDesempeno resultadosDesempeno = obtenerHabilidades();

        if (!resultadosDesempeno.getHabilidades().isEmpty()) {
            viewModel.guardarPruebaDesempeno(candidatoId, resultadosDesempeno);
            viewModel.getPruebaDesempenoGuardada().observe(this, exito -> {
                if (exito) {
                    // La prueba de desempeño se guardó exitosamente
                    Toast.makeText(this, "Prueba de desempeño guardada", Toast.LENGTH_SHORT).show();
                    // Agregar un temporizador para cerrar la actividad después de un breve período
                    new android.os.Handler().postDelayed(
                            () -> finish(), // Cierra la actividad después de guardar
                            1000 // Tiempo de espera en milisegundos (ajusta según sea necesario)
                    );
                } else {
                    // Hubo un error al guardar la prueba de desempeño
                    Toast.makeText(this, "Error al guardar la prueba de desempeño", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Agrega al menos una habilidad antes de guardar", Toast.LENGTH_SHORT).show();
        }
    }

    private ResultadosDesempeno obtenerHabilidades() {
        List<HabilidadPuntaje> habilidades = new ArrayList<>();
        LinearLayout containerSkills = findViewById(R.id.containerSkills);

        for (int i = 0; i < containerSkills.getChildCount(); i++) {
            View habilidadView = containerSkills.getChildAt(i);

            EditText editTextSkill = habilidadView.findViewById(R.id.editTextSkill);
            RatingBar ratingBarSkill = habilidadView.findViewById(R.id.ratingBarSkill);

            if (editTextSkill != null) {
                String nombreHabilidad = editTextSkill.getText().toString();
                float puntaje = ratingBarSkill.getRating();

                if (!nombreHabilidad.isEmpty()) {
                    habilidades.add(new HabilidadPuntaje(nombreHabilidad, (int) puntaje));
                }
            }
        }

        // Obtener otros datos necesarios para el objeto ResultadosDesempeno
        int idPrueba = 1;
        int idCandidato = 1;
        int idEmpresa = 1;

        return new ResultadosDesempeno(idPrueba, idCandidato, idEmpresa, habilidades);
    }

    private void agregarHabilidad() {
        LinearLayout containerSkills = findViewById(R.id.containerSkills);

        // Crear una nueva vista de habilidad técnica
        View nuevaHabilidadView = getLayoutInflater().inflate(R.layout.item_habilidad_tecnica, containerSkills, false);

        // Obtener referencias a las vistas dentro de la nueva habilidad técnica
        EditText editTextSkill = nuevaHabilidadView.findViewById(R.id.editTextSkill);
        ImageButton btnRemoveSkill = nuevaHabilidadView.findViewById(R.id.btnRemoveSkill);

        // Establecer un ID único para el campo de texto y el botón de eliminación
        int uniqueId = View.generateViewId();
        editTextSkill.setId(uniqueId);
        btnRemoveSkill.setId(uniqueId);

        // Configurar el botón para quitar la habilidad técnica
        btnRemoveSkill.setOnClickListener(v -> quitarHabilidad(nuevaHabilidadView));

        // Agregar la nueva habilidad técnica al contenedor
        containerSkills.addView(nuevaHabilidadView, containerSkills.getChildCount() - 1);
    }

    private void quitarHabilidad(View habilidadView) {
        LinearLayout containerSkills = findViewById(R.id.containerSkills);
        containerSkills.removeView(habilidadView);
    }
}
