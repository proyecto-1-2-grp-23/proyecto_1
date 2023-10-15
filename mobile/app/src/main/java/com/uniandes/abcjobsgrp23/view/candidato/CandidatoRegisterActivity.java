package com.uniandes.abcjobsgrp23.view.candidato;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import com.uniandes.abcjobsgrp23.R;

public class CandidatoRegisterActivity  extends AppCompatActivity {

    private EditText editNombre, editEdad, editTelefono, editEmail, editCity, editLanguages, editTraits, editPassword, editConfirmPassword;
    private AutoCompleteTextView autoCompleteCountry;
    private Button buttonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_register);

        editNombre = findViewById(R.id.editTextName);
        editEdad = findViewById(R.id.editTextAge);
        editTelefono = findViewById(R.id.editTextPhone);
        editEmail = findViewById(R.id.editTextEmail);
        autoCompleteCountry = findViewById(R.id.autoCompleteCountry);
        editCity = findViewById(R.id.editTextCity);
        editLanguages = findViewById(R.id.editTextLanguages);
        editTraits = findViewById(R.id.editTextTraits);
        editPassword = findViewById(R.id.editTextPassword);
        editConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegistrar = findViewById(R.id.buttonRegister);

        // Configurar el AutoCompleteTextView para los países
        String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        autoCompleteCountry.setAdapter(adapter);

        // Aquí puedes agregar la lógica de registro cuando se presione el botón "Registrar"
        buttonRegistrar.setOnClickListener(v -> {
            // Obtener los valores de los campos y realizar la lógica de registro
            String nombre = editNombre.getText().toString();
            String edad = editEdad.getText().toString();
            String telefono = editTelefono.getText().toString();
            String email = editEmail.getText().toString();
            String country = autoCompleteCountry.getText().toString();
            String city = editCity.getText().toString();
            String languages = editLanguages.getText().toString();
            String traits = editTraits.getText().toString();
            String password = editPassword.getText().toString();
            String confirmPassword = editConfirmPassword.getText().toString();

            // Realizar la lógica de registro aquí
        });
    }
}