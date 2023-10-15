package com.uniandes.abcjobsgrp23.view.candidato;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uniandes.abcjobsgrp23.PostLoginActivity;
import com.uniandes.abcjobsgrp23.R;
//import com.uniandes.abcjobsgrp23.viewmodel.UsuarioLoginViewModel;

public class CandidatoLoginActivity extends AppCompatActivity {
//    private UsuarioLoginViewModel usuarioLoginViewModel;

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        // Configurar el evento de clic para el botón de inicio de sesión
        btnLogin.setOnClickListener(view -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();


            if (isValidLogin(username, password)) {
                Intent intent = new Intent(CandidatoLoginActivity.this, PostLoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(CandidatoLoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(CandidatoLoginActivity.this, CandidatoRegisterActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidLogin(String username, String password) {
        return username.equals("usuario") && password.equals("12345");
    }
}