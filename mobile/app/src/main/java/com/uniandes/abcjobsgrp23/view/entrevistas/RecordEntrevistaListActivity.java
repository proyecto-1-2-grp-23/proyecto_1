package com.uniandes.abcjobsgrp23.view.entrevistas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.RecordEntrevista;
import com.uniandes.abcjobsgrp23.viewmodel.EntrevistaViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecordEntrevistaListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecordEntrevistaAdapter adapter;
    private EntrevistaViewModel entrevistaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_entrevista_list);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa el ViewModel
        entrevistaViewModel = new ViewModelProvider(this).get(EntrevistaViewModel.class);

        // Inicializa el adaptador (pasa una lista vacía inicialmente)
        adapter = new RecordEntrevistaAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Observa los cambios en la lista de entrevistas
        entrevistaViewModel.getAllEntrevistas().observe(this, entrevistas -> {
            // Actualiza el adaptador con la nueva lista de entrevistas
            adapter.setEntrevistas(entrevistas);
        });
    }
}

