package com.uniandes.abcjobsgrp23;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.uniandes.abcjobsgrp23.view.candidato.CandidatoLoginActivity;
import com.uniandes.abcjobsgrp23.view.empresa.EmpresaLoginActivity;

public class PreLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        Button btnEmpresa = findViewById(R.id.btnEmpresa);
        Button btnCandidato = findViewById(R.id.btnCandidato);

        btnEmpresa.setOnClickListener(v -> {
            // Redirigir a la pantalla de inicio de sesión de empresas
            Intent intent = new Intent(PreLoginActivity.this, EmpresaLoginActivity.class);
            startActivity(intent);
        });

        btnCandidato.setOnClickListener(v -> {
            // Redirigir a la pantalla de inicio de sesión de candidatos
            Intent intent = new Intent(PreLoginActivity.this, CandidatoLoginActivity.class);
            startActivity(intent);
        });
    }
}