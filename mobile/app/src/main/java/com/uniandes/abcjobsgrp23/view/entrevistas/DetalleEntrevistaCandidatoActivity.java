package com.uniandes.abcjobsgrp23.view.entrevistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import androidx.appcompat.app.AppCompatActivity;


import com.uniandes.abcjobsgrp23.PostLoginActivity;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.Candidato;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.viewmodel.CandidatoViewModel;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetalleEntrevistaCandidatoActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private boolean isDatePickerVisible = false;
    private boolean isTimePickerVisible = false;
    boolean isEditable = true;
    private TextView textViewFechaSeleccionada;
    private TextView textViewHoraSeleccionada;

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

        Button btnGuardar = findViewById(R.id.bntGuardarDetalleEntervista);
        Button btnCancelar = findViewById(R.id.bntCancelarDetalleEntervista);

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

        // Recupera los datos pasados desde la actividad anterior
        Bundle extras = getIntent().getExtras();

        List<String> empresas = new ArrayList<>();
        List<String> candidatos = new ArrayList<>();

        // Encuentra los elementos del diseño
        Spinner spinnerFuncionario = findViewById(R.id.spinnerFuncionario);
        Spinner spinnerCandidato = findViewById(R.id.spinnerCandidato);
        Spinner spinnerEmpresa = findViewById(R.id.spinnerEmpresa);
        DatePicker datePicker = findViewById(R.id.datePicker);
        TimePicker timePicker = findViewById(R.id.timePicker);
        EditText editTextLugarUrl = findViewById(R.id.editTextLugarUrl);

        if (extras != null) {
            String titulo = extras.getString("correoCandidato");
            String descripcion = extras.getString("nombreEmpresa");
            String fechaEntrevista = extras.getString("fechaEntrevista");
            isEditable = extras.getBoolean("Editar", true);

            if (!isEditable) {
                btnMostrarDatePicker.setEnabled(false);
                btnMostrarTimePicker.setEnabled(false);
                dateContainer.setVisibility(View.GONE);
                timeContainer.setVisibility(View.GONE);
                btnGuardar.setVisibility(View.VISIBLE);
                btnCancelar.setVisibility(View.VISIBLE);

                candidatos.add(extras.getString("nombreCandidato"));
                empresas.add(extras.getString("nombreEmpresa"));


                // Dividir la cadena utilizando '-' y 'T'
                String[] partesFecha = fechaEntrevista.split("[-T]");

                // Obtener los valores de año, mes y día
                int year = Integer.parseInt(partesFecha[0]);
                int month = Integer.parseInt(partesFecha[1]) - 1; // Restar 1 porque en DatePicker, enero es 0
                int day = Integer.parseInt(partesFecha[2]);

                // Actualizar el DatePicker
                datePicker.updateDate(year, month, day);
                timePicker.setHour(12);
                timePicker.setMinute(0);

                editTextLugarUrl.setText(extras.getString("lugarEntrevista"));
            }else {

                // Establecer fecha y hora de prueba
                datePicker.updateDate(2023, 10, 29);
                timePicker.setHour(12);
                timePicker.setMinute(0);

                candidatos.add("David Morales Aguilar");
                candidatos.add("Luis Felipe Cruz");

                empresas.add("Globant");
                empresas.add("ScotiaBank");
                // Establecer lugar/URL de prueba
                editTextLugarUrl.setText("https://example.com");

            }

            // Encuentra los TextViews en tu diseño para mostrar los datos
            TextView textViewTitulo = findViewById(R.id.textViewDetalleTitulo);
            TextView textViewDescripcion = findViewById(R.id.textViewDetalleDescripcion);

            // Establece los datos en los TextViews
            textViewTitulo.setText(titulo);
            textViewDescripcion.setText(descripcion);
        }

        // Bloquea o desbloquea los campos según el valor de isEditable
        spinnerFuncionario.setEnabled(isEditable);
        spinnerCandidato.setEnabled(isEditable);
        spinnerEmpresa.setEnabled(isEditable);
        datePicker.setEnabled(isEditable);
        timePicker.setEnabled(isEditable);
        editTextLugarUrl.setEnabled(isEditable);

        // Datos de prueba
        List<String> funcionarios = new ArrayList<>();
        funcionarios.add("Funcionario 1");
        funcionarios.add("Funcionario 2");
        funcionarios.add("Funcionario 3");

        ArrayAdapter<String> funcionarioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, funcionarios);
        spinnerFuncionario.setAdapter(funcionarioAdapter);

        // Inicializa el ViewModel
        //candidatoViewModel = new ViewModelProvider(this).get(CandidatoViewModel.class);

        ArrayAdapter<String> candidatoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, candidatos);
        spinnerCandidato.setAdapter(candidatoAdapter);


        ArrayAdapter<String> empresaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, empresas);
        spinnerEmpresa.setAdapter(empresaAdapter);




    }
}