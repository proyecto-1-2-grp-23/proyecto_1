package com.uniandes.abcjobsgrp23.view.entrevistas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.RecordEntrevista;
import java.util.ArrayList;
import java.util.List;

public class RecordEntrevistaListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecordEntrevistaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_entrevista_list);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crea una lista de registros de entrevistas (puedes cargarla desde una fuente de datos)
        List<RecordEntrevista> recordList = new ArrayList<>();
        recordList.add(new RecordEntrevista("Título 1", "Descripción 1"));
        recordList.add(new RecordEntrevista("Título 2", "Descripción 2"));

        // Inicializa el adaptador y enlaza el RecyclerView
        adapter = new RecordEntrevistaAdapter(recordList);
        recyclerView.setAdapter(adapter);
    }
}

