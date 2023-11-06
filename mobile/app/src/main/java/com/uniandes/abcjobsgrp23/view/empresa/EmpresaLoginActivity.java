package com.uniandes.abcjobsgrp23.view.empresa;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uniandes.abcjobsgrp23.PostLoginActivity;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.view.candidato.CandidatoRegisterActivity;

public class EmpresaLoginActivity extends AppCompatActivity {

    public EditText editTextUsername;
    public EditText editTextPassword;
    public Button btnLogin;
    private Button btnRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_login);

        editTextUsername = findViewById(R.id.textEmpresaUsername);
        editTextPassword = findViewById(R.id.textEmpresaPasword);

        btnLogin = findViewById(R.id.btnLoginEmpresaAction);
        btnRegister = findViewById(R.id.btnRegisterEmpresaAction);

        // Inicialmente, deshabilita el botón de inicio de sesión
        btnLogin.setEnabled(false);

        // Configurar el evento de clic para el botón de inicio de sesión
        btnLogin.setOnClickListener(view -> onLoginButtonClick());

        btnRegister.setOnClickListener(view -> onRegisterButtonClick());

        initTextWatcher();
    }


    public void initTextWatcher() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableLoginButtonIfFieldsAreFilled();
            }
        };

        editTextUsername.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);
    }

    private void onLoginButtonClick() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        if (username.equalsIgnoreCase("empresa") && password.equalsIgnoreCase("1234")) {
            UserType.setUserType(UserType.EMPRESA);
            startPostLoginActivity();
        } else {
            showIncorrectCredentialsMessage();
        }

    }

    private void onRegisterButtonClick() {
        Intent intent = new Intent(EmpresaLoginActivity.this, CandidatoRegisterActivity.class);
        startActivity(intent);
    }

    public void enableLoginButtonIfFieldsAreFilled() {
        boolean isUsernameEmpty = editTextUsername.getText().toString().isEmpty();
        boolean isPasswordEmpty = editTextPassword.getText().toString().isEmpty();
        btnLogin.setEnabled(!isUsernameEmpty && !isPasswordEmpty);
    }

    private void startPostLoginActivity() {
        Intent intent = new Intent(EmpresaLoginActivity.this, PostLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showIncorrectCredentialsMessage() {
        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
    }

}
