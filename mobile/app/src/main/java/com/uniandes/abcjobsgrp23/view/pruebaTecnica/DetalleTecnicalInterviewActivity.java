package com.uniandes.abcjobsgrp23.view.pruebaTecnica;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.uniandes.abcjobsgrp23.R;

public class DetalleTecnicalInterviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tecnical_interview);

        // Obtén los datos pasados desde la actividad anterior
        String titulo = getIntent().getStringExtra("titulo");
        String nombreProyecto = getIntent().getStringExtra("nombreProyecto");
        String nombreCandidato = getIntent().getStringExtra("nombreCandidato");
        String correoCandidato = getIntent().getStringExtra("correoCandidato");
        boolean editar = getIntent().getBooleanExtra("Editar", false);

        // Configura las vistas con los datos
        TextView titleTextView = findViewById(R.id.textViewTitulo);
//        TextView proyectoTextView = findViewById(R.id.textViewProyecto);
        TextView candidatoTextView = findViewById(R.id.textViewCandidato);
        TextView correoTextView = findViewById(R.id.textViewCorreo);

        titleTextView.setText(titulo);
//        proyectoTextView.setText(nombreProyecto);
        candidatoTextView.setText(nombreCandidato);
        correoTextView.setText(correoCandidato);

        if (editar) {
            // Habilita la lógica de edición si es necesario
            // Por ejemplo, puedes mostrar campos de edición y botones de guardado.
        }
    }

}
