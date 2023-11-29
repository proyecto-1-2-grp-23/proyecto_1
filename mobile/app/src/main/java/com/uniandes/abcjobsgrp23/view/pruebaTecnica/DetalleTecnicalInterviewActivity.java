package com.uniandes.abcjobsgrp23.view.pruebaTecnica;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.uniandes.abcjobsgrp23.R;

public class DetalleTecnicalInterviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tecnical_interview);

        // Obtén los datos pasados desde la actividad anterior
        String titulo = getIntent().getStringExtra("titulo");
        String observaciones = getIntent().getStringExtra("observaciones");
        String nombreProyecto = getIntent().getStringExtra("nombreProyecto");
        String candidatoSeleccionado = getIntent().getStringExtra("idCandidato") + ") " + getIntent().getStringExtra("nombreCandidato");
        boolean editar = getIntent().getBooleanExtra("Editar", false);

        // Configura las vistas con los datos
        TextView titleTextView = findViewById(R.id.textViewTitulo);
        TextInputLayout observacionesTextInputLayout = findViewById(R.id.textInputLayout);
        Spinner candidatoSpinner = findViewById(R.id.spinnerCandidatos);
        Spinner proyectoSpinner = findViewById(R.id.spinnerProyectos);


        titleTextView.setText(titulo);
        observacionesTextInputLayout.getEditText().setText(observaciones);

        // Configura el Spinner de Candidatos
        ArrayAdapter<String> candidatoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        candidatoAdapter.add(candidatoSeleccionado);
        candidatoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        candidatoSpinner.setAdapter(candidatoAdapter);

        // Configura el Spinner de Proyectos
        ArrayAdapter<String> proyectoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        proyectoAdapter.add(nombreProyecto);
        proyectoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proyectoSpinner.setAdapter(proyectoAdapter);

        // Deshabilitar la edición de los Spinners
        candidatoSpinner.setEnabled(false);
        proyectoSpinner.setEnabled(false);

        if (editar) {
            // Habilita la lógica de edición si es necesario

        }
    }

}
