package com.uniandes.abcjobsgrp23.view.entrevistas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.uniandes.abcjobsgrp23.PostLoginActivity;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;

public class ResultadoEntrevistaActivity extends AppCompatActivity {

    private EditText editTextEstadoFinal;
    private EditText editTextDescripcion;
    private EditText editTextObservaciones;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_entrevista);

        editTextEstadoFinal = findViewById(R.id.editTextEstadoFinalResEntre);
        editTextDescripcion = findViewById(R.id.editTextDescripcionResEntre);
        editTextObservaciones = findViewById(R.id.editTextObservacionesResEntre);
        btnGuardar = findViewById(R.id.btnGuardarResEntre);

        // Agrega un TextWatcher a los campos de entrada de texto
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Verifica si todos los campos tienen contenido
                boolean camposCompletos = !editTextEstadoFinal.getText().toString().isEmpty() &&
                        !editTextDescripcion.getText().toString().isEmpty() &&
                        !editTextObservaciones.getText().toString().isEmpty();
                // Habilita o deshabilita el botón de guardar según los campos completos
                btnGuardar.setEnabled(camposCompletos);
            }
        };

        editTextEstadoFinal.addTextChangedListener(textWatcher);
        editTextDescripcion.addTextChangedListener(textWatcher);
        editTextObservaciones.addTextChangedListener(textWatcher);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Maneja el clic del botón de guardar
                guardarResultado();
                UserType.setUserType(UserType.EMPRESA);
                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), PostLoginActivity.class);
                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });

        // Configura el botón de cancelar para finalizar la actividad
        Button btnCancelar = findViewById(R.id.btnCancelarResEntre);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserType.setUserType(UserType.EMPRESA);
                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), PostLoginActivity.class);
                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });
    }

    private void guardarResultado() {
        String estadoFinal = editTextEstadoFinal.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        String observaciones = editTextObservaciones.getText().toString();

    }
}
