package com.uniandes.abcjobsgrp23.view.candidato;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.uniandes.abcjobsgrp23.R;

public class CandidatoRegisterActivity extends AppCompatActivity {

    private EditText editNombre, editEdad, editTelefono, editEmail, editCity, editLanguages, editTraits, editPassword, editConfirmPassword;
    private AutoCompleteTextView autoCompleteCountry;
    private Button buttonRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidato_register);

        editNombre = findViewById(R.id.register_nombre_candidato);
        editEdad = findViewById(R.id.register_edad_candidato);
        editTelefono = findViewById(R.id.register_telefono_candidato);
        editEmail = findViewById(R.id.register_correo_candidato);
        autoCompleteCountry = findViewById(R.id.register_pais_candidato);
        editCity = findViewById(R.id.register_ciudad_candidato);
        editLanguages = findViewById(R.id.register_idiomas_candidato);
        editTraits = findViewById(R.id.register_rasgos_personales_candidato);
        editPassword = findViewById(R.id.register_pasw_1_candidato);
        editConfirmPassword = findViewById(R.id.register_pasw_2_candidato);
        buttonRegistrar = findViewById(R.id.buttonRegisterCandidato);

        // Configurar el AutoCompleteTextView para los países
        String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        autoCompleteCountry.setAdapter(adapter);

        // Inicialmente, deshabilita el botón de registro
        buttonRegistrar.setEnabled(false);

        // Agrega un TextWatcher para habilitar o deshabilitar el botón en función de los campos llenos
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Verifica si todos los campos de entrada están llenos y si las contraseñas coinciden
                boolean allFieldsFilled = isAllFieldsFilled();
                boolean passwordsMatch = doPasswordsMatch();
                // Habilita o deshabilita el botón de registro según el estado de los campos y las contraseñas
                buttonRegistrar.setEnabled(allFieldsFilled && passwordsMatch);
            }
        };

        // Agrega el TextWatcher a todos los campos de entrada
        editNombre.addTextChangedListener(textWatcher);
        editEdad.addTextChangedListener(textWatcher);
        editTelefono.addTextChangedListener(textWatcher);
        editEmail.addTextChangedListener(textWatcher);
        autoCompleteCountry.addTextChangedListener(textWatcher);
        editCity.addTextChangedListener(textWatcher);
        editLanguages.addTextChangedListener(textWatcher);
        editTraits.addTextChangedListener(textWatcher);
        editPassword.addTextChangedListener(textWatcher);
        editConfirmPassword.addTextChangedListener(textWatcher);

        // Agrega la lógica de registro cuando se presiona el botón "Registrar"
        buttonRegistrar.setOnClickListener(v -> {
            // Obtén los valores de los campos y realiza la lógica de registro
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

            // Realiza la lógica de registro aquí
        });
    }

    // Método para verificar si todos los campos de entrada están llenos
    private boolean isAllFieldsFilled() {
        return !editNombre.getText().toString().isEmpty() &&
                !editEdad.getText().toString().isEmpty() &&
                !editTelefono.getText().toString().isEmpty() &&
                !editEmail.getText().toString().isEmpty() &&
                !autoCompleteCountry.getText().toString().isEmpty() &&
                !editCity.getText().toString().isEmpty() &&
                !editLanguages.getText().toString().isEmpty() &&
                !editTraits.getText().toString().isEmpty() &&
                !editPassword.getText().toString().isEmpty() &&
                !editConfirmPassword.getText().toString().isEmpty();
    }

    // Método para verificar si ambas contraseñas coinciden
    private boolean doPasswordsMatch() {
        String password1 = editPassword.getText().toString();
        String password2 = editConfirmPassword.getText().toString();
        return password1.equals(password2);
    }
}
