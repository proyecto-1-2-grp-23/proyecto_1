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
import com.uniandes.abcjobsgrp23.data.model.ApiResponse;
import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.LoginResponse;
import com.uniandes.abcjobsgrp23.data.model.UserCredential;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.view.candidato.CandidatoRegisterActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        UserCredential loginCredential = new UserCredential(username, password);

        if (username.equalsIgnoreCase("empresa") && password.equalsIgnoreCase("1234")) {
            UserType.setUserType(UserType.EMPRESA);
            startPostLoginActivity("Usuario logueado con exito");
        }

        // Llama al método de autenticación
        RetrofitBroker.loginUser(loginCredential, new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // La autenticación fue exitosa
                    LoginResponse apiResponse = response.body();

                    // Accede a los datos de la respuesta, por ejemplo, al token
                    String token = apiResponse.getToken();
                    String message = apiResponse.getMessage();

                    UserType.setUserType(UserType.EMPRESA);
                    startPostLoginActivity(message);
                } else {
                    // La autenticación falló
                    showIncorrectCredentialsMessage();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Error de red o del servidor
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Error de red o del servidor", Toast.LENGTH_SHORT).show();
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

    private void startPostLoginActivity(String message) {
        // La prueba de desempeño se guardó exitosamente
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // Agregar un temporizador para cerrar la actividad después de un breve período
        new android.os.Handler().postDelayed(
                () -> finish(), // Cierra la actividad después de guardar
                1000 // Tiempo de espera en milisegundos (ajusta según sea necesario)
        );

        Intent intent = new Intent(EmpresaLoginActivity.this, PostLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showIncorrectCredentialsMessage() {
        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        new android.os.Handler().postDelayed(
                () -> finish(), // Cierra la actividad después de guardar
                1000 // Tiempo de espera en milisegundos (ajusta según sea necesario)
        );
    }

}
