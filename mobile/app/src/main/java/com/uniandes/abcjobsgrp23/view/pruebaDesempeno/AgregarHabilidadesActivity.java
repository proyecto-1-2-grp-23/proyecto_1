package com.uniandes.abcjobsgrp23.view.pruebaDesempeno;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.uniandes.abcjobsgrp23.R;

public class AgregarHabilidadesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evaluacion_desempeno);  // Actualiza aquí el nombre del layout

        LinearLayout containerSkills = findViewById(R.id.containerSkills);
        Button btnAddSkill = findViewById(R.id.btnAddSkill);

        btnAddSkill.setOnClickListener(v -> agregarHabilidad());
    }

    private void agregarHabilidad() {
        LinearLayout containerSkills = findViewById(R.id.containerSkills);

        // Crear una nueva vista de habilidad técnica
        View nuevaHabilidadView = getLayoutInflater().inflate(R.layout.item_habilidad_tecnica, containerSkills, false);

        // Obtener referencias a las vistas dentro de la nueva habilidad técnica
        EditText editTextSkill = nuevaHabilidadView.findViewById(R.id.editTextSkill);
        ImageButton btnRemoveSkill = nuevaHabilidadView.findViewById(R.id.btnRemoveSkill);

        // Configurar el botón para quitar la habilidad técnica
        btnRemoveSkill.setOnClickListener(v -> quitarHabilidad(nuevaHabilidadView));

        // Agregar la nueva habilidad técnica al contenedor
        containerSkills.addView(nuevaHabilidadView);
    }

    private void quitarHabilidad(View habilidadView) {
        LinearLayout containerSkills = findViewById(R.id.containerSkills);
        containerSkills.removeView(habilidadView);
    }
}
