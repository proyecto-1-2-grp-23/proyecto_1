package com.uniandes.abcjobsgrp23.view.candidato;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.uniandes.abcjobsgrp23.PostLoginActivity;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.repository.UserCredentialRespository;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidatoLoginActivity extends AppCompatActivity {
    public EditText editTextUsername;
    public EditText editTextPassword;
    public Button btnLogin;
    private Button btnRegister;
    UserCredentialRespository userCredentialRespository = new UserCredentialRespository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.register_pasw_1_candidato);

        btnLogin = findViewById(R.id.btnLoginCandidato);
        btnRegister = findViewById(R.id.btnRegisterCandidato);

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

        if (username.equalsIgnoreCase("candidato") && password.equalsIgnoreCase("1234")) {
            UserType.setUserType(UserType.CANDIDATO);
            startPostLoginActivity();
        } else {
            showIncorrectCredentialsMessage();
        }

        /**
        userCredentialRespository.login(username, password, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String jsonResponse = response.body().string();
                        JSONObject jsonObject = new JSONObject(jsonResponse);
                        if (jsonObject.has("token")) {
                            String authToken = jsonObject.getString("token");
                            //saveAuthToken(authToken);
                            UserType.setUserType(UserType.CANDIDATO);
                            startPostLoginActivity();
                        } else {
                            showErrorMessage("Error: Token no encontrado en la respuesta");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showErrorMessage("Error en el formato de respuesta JSON");
                    } catch (IOException e) {
                        e.printStackTrace();
                        showErrorMessage("Error al procesar la respuesta");
                    }
                } else {
                    showErrorMessage("Error en la solicitud (código " + response.code() + ")");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showErrorMessage("Error en la red: " + t.getMessage());
            }
        });
         **/
    }

    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void saveAuthToken(String authToken) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("authToken", authToken);
        editor.apply();
    }

    private void onRegisterButtonClick() {
        Intent intent = new Intent(CandidatoLoginActivity.this, CandidatoRegisterActivity.class);
        startActivity(intent);
    }

    public void enableLoginButtonIfFieldsAreFilled() {
        boolean isUsernameEmpty = editTextUsername.getText().toString().isEmpty();
        boolean isPasswordEmpty = editTextPassword.getText().toString().isEmpty();
        btnLogin.setEnabled(!isUsernameEmpty && !isPasswordEmpty);
    }

    private void startPostLoginActivity() {
        Intent intent = new Intent(CandidatoLoginActivity.this, PostLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showIncorrectCredentialsMessage() {
        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
    }
}
