package com.uniandes.abcjobsgrp23.view.entrevistas;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uniandes.abcjobsgrp23.PostLoginActivity;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.data.model.Empresa;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.model.Funcionario;
import com.uniandes.abcjobsgrp23.data.model.LoginResponse;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.view.empresa.EmpresaLoginActivity;
import com.uniandes.abcjobsgrp23.viewmodel.CandidatoViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEntrevistaCandidatoActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private boolean isDatePickerVisible = false;
    private boolean isTimePickerVisible = false;
    boolean isEditable = true;
    private TextView textViewFechaSeleccionada;
    private TextView textViewHoraSeleccionada;
    private EditText editTextLugarUrl;

    private Spinner spinnerFuncionario;
    private Spinner spinnerCandidato;
    private Spinner spinnerEmpresa;

    private CandidatoViewModel candidatoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_entrevista_candidato);

        textViewFechaSeleccionada = findViewById(R.id.textViewFechaSeleccionada);
        textViewHoraSeleccionada = findViewById(R.id.textViewHoraSeleccionada);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        Button btnMostrarDatePicker = findViewById(R.id.btnMostrarDatePicker);
        Button btnMostrarTimePicker = findViewById(R.id.btnMostrarTimePicker);
        LinearLayout dateContainer = findViewById(R.id.dateContainer);
        LinearLayout timeContainer = findViewById(R.id.TimeContainer);

        spinnerFuncionario = findViewById(R.id.spinnerFuncionario);
        spinnerCandidato = findViewById(R.id.spinnerCandidato);
        spinnerEmpresa = findViewById(R.id.spinnerEmpresa);
        editTextLugarUrl = findViewById(R.id.editTextLugarUrl);

        DatePicker datePicker = findViewById(R.id.datePicker);
        TimePicker timePicker = findViewById(R.id.timePicker);

        Button btnGuardar = findViewById(R.id.bntGuardarDetalleEntervista);
        Button btnCancelar = findViewById(R.id.bntCancelarDetalleEntervista);

        cargarInformacionEnSpinners();

        btnMostrarDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDatePickerVisible = !isDatePickerVisible;
                dateContainer.setVisibility(isDatePickerVisible ? View.VISIBLE : View.GONE);
                timeContainer.setVisibility(View.GONE);
            }
        });

        btnMostrarTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTimePickerVisible = !isTimePickerVisible;
                timeContainer.setVisibility(isTimePickerVisible ? View.VISIBLE : View.GONE);
                dateContainer.setVisibility(View.GONE);
            }
        });

        // Configurar el DatePicker después de obtener la referencia
        datePicker.init(2023, 10, 29, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Actualizar el TextView con la fecha seleccionada
                String fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                textViewFechaSeleccionada.setText("Fecha seleccionada: " + fechaSeleccionada);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Actualizar el TextView con la hora seleccionada
                String horaSeleccionada = String.format("%02d:%02d", hourOfDay, minute);
                textViewHoraSeleccionada.setText("Hora seleccionada: " + horaSeleccionada);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserType.setUserType(UserType.EMPRESA);
                Intent intent = new Intent(v.getContext(), PostLoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserType.setUserType(UserType.EMPRESA);
                Intent intent = new Intent(v.getContext(), PostLoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    private void cargarInformacionEnSpinners() {
        // Configurar el adapter para el Spinner de Candidatos
        cargarFuncionarios();
        // Configurar el adapter para el Spinner de Candidatos
        cargarCandidatos();
        // Configurar el adapter para el Spinner de Empresas
        cargarEmpresas();
    }

    private void cargarFuncionarios() {
        RetrofitBroker.getAllFunsionarios(new retrofit2.Callback<List<Funcionario>>() {
            @Override
            public void onResponse(Call<List<Funcionario>> call, Response<List<Funcionario>> response) {
                // Crear una lista de strings con la información deseada
                List<String> funcionarioStrings = new ArrayList<>();
                for (Funcionario funcionario : response.body()) {
                    String funcionarioInfo = funcionario.getId() + ") " + funcionario.getNombreFuncionario();
                    funcionarioStrings.add(funcionarioInfo);
                }
                // Crear un ArrayAdapter con la lista de strings
                ArrayAdapter<String> funcionarioAdapter = new ArrayAdapter<>(CreateEntrevistaCandidatoActivity.this, android.R.layout.simple_spinner_item, funcionarioStrings);
                funcionarioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFuncionario.setAdapter(funcionarioAdapter);
            }

            @Override
            public void onFailure(Call<List<Funcionario>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CreateEntrevistaCandidatoActivity.this, "Fallo al obtener candidatos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarCandidatos() {
        RetrofitBroker.getAllUsersCandidatos(new retrofit2.Callback<List<Candidato>>() {
            @Override
            public void onResponse(Call<List<Candidato>> call, Response<List<Candidato>> response) {
                // Crear una lista de strings con la información deseada
                List<String> candidatoStrings = new ArrayList<>();
                for (Candidato candidato : response.body()) {
                    String candidatoInfo = candidato.getId() + ") " + candidato.getNombreCompleto();
                    candidatoStrings.add(candidatoInfo);
                }
                // Crear un ArrayAdapter con la lista de strings
                ArrayAdapter<String> candidatosAdapter = new ArrayAdapter<>(CreateEntrevistaCandidatoActivity.this, android.R.layout.simple_spinner_item, candidatoStrings);
                candidatosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCandidato.setAdapter(candidatosAdapter);
            }

            @Override
            public void onFailure(Call<List<Candidato>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CreateEntrevistaCandidatoActivity.this, "Fallo al obtener candidatos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarEmpresas() {
        RetrofitBroker.getAllEmpresas(new retrofit2.Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                // Crear una lista de strings con la información deseada
                List<String> empresaStrings = new ArrayList<>();
                for (Empresa empresa : response.body()) {
                    String empresaInfo = empresa.getId() + ") " + empresa.getRazonSocial() + "- " + empresa.getTipoEmpresa();
                    empresaStrings.add(empresaInfo);
                }
                // Crear un ArrayAdapter con la lista de strings
                ArrayAdapter<String> empresasAdapter = new ArrayAdapter<>(CreateEntrevistaCandidatoActivity.this, android.R.layout.simple_spinner_item, empresaStrings);
                empresasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerEmpresa.setAdapter(empresasAdapter);
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CreateEntrevistaCandidatoActivity.this, "Fallo al obtener empresas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enviarPeticionEntrevista() {
        // Obtener valores seleccionados de los spinners
        String funcionarioSeleccionado = (String) spinnerFuncionario.getSelectedItem();
        String candidatoSeleccionado = (String) spinnerCandidato.getSelectedItem();
        String empresaSeleccionada = (String) spinnerEmpresa.getSelectedItem();

        // Extraer IDs de las cadenas seleccionadas
        int idFuncionario = Integer.parseInt(funcionarioSeleccionado.split("\\) ")[0]);
        int idCandidato = Integer.parseInt(candidatoSeleccionado.split("\\) ")[0]);
        int idEmpresa = Integer.parseInt(empresaSeleccionada.split("\\) ")[0]);

        // Obtener fecha y hora seleccionadas
        String fechaSeleccionada = obtenerFechaSeleccionada();
        String lugar = obtenerLugarSeleccionado();

        // Construir la entidad para la petición
        Entrevista entrevistaRequest = new Entrevista(idFuncionario, idEmpresa, idCandidato, fechaSeleccionada, lugar);

        // Enviar la petición a la API externa
        RetrofitBroker.createEntrevista(entrevistaRequest, new retrofit2.Callback<Entrevista>() {
            @Override
            public void onResponse(Call<Entrevista> call, Response<Entrevista> response) {
                if (response.isSuccessful()) {
                    // La autenticación fue exitosa
                    Entrevista apiResponse = response.body();
                    startPostCreateActivity();
                } else {
                    // La autenticación falló
                    showIncorrectCreationsMessage();
                }
            }

            @Override
            public void onFailure(Call<Entrevista> call, Throwable t) {
                // Manejar fallo
                Toast.makeText(CreateEntrevistaCandidatoActivity.this, "Fallo al obtener empresas", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startPostCreateActivity() {
        // La prueba de desempeño se guardó exitosamente
        Toast.makeText(this, "Se creo la entrevista con exito", Toast.LENGTH_SHORT).show();
        // Agregar un temporizador para cerrar la actividad después de un breve período
        new android.os.Handler().postDelayed(
                () -> finish(), // Cierra la actividad después de guardar
                1000 // Tiempo de espera en milisegundos (ajusta según sea necesario)
        );
        finish();
    }

    private void showIncorrectCreationsMessage() {
        Toast.makeText(this, "No fue posible crear la entrevista", Toast.LENGTH_SHORT).show();
        new android.os.Handler().postDelayed(
                () -> finish(), // Cierra la actividad después de guardar
                1000 // Tiempo de espera en milisegundos (ajusta según sea necesario)
        );
    }

    private String obtenerFechaSeleccionada() {
        int dayOfMonth = datePicker.getDayOfMonth();
        int monthOfYear = datePicker.getMonth() + 1; // Agregar 1 porque en DatePicker, enero es 0
        int year = datePicker.getYear();

        int hourOfDay = timePicker.getHour();
        int minute = timePicker.getMinute();

        return String.format("%04d-%02d-%02d %02d:%02d:00", year, monthOfYear, dayOfMonth, hourOfDay, minute);
    }

    private String obtenerLugarSeleccionado() {
        return editTextLugarUrl.getText().toString().trim();
    }

}
