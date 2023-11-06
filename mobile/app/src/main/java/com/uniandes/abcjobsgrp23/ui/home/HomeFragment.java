package com.uniandes.abcjobsgrp23.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.RecordEntrevista;
import com.uniandes.abcjobsgrp23.databinding.FragmentHomeBinding;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.view.entrevistas.RecordEntrevistaAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<RecordEntrevista> recordList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;

        // Obtiene el tipo de usuario
        String userType = UserType.getUserType();

        // Muestra contenido en función del tipo de usuario
        if (userType == UserType.CANDIDATO) {
            textView.setText("Home, Candidato.");
        } else if (userType == UserType.EMPRESA) {
            textView.setText("Home, Empresa.");

            // Inicializa y configura el RecyclerView para RecordEntrevista
            RecyclerView recyclerViewRecordEntrevista = root.findViewById(R.id.recyclerViewRecordEntrevista);
            recyclerViewRecordEntrevista.setLayoutManager(new LinearLayoutManager(requireContext()));

            // Agrega datos de ejemplo a la lista de RecordEntrevista
            List<RecordEntrevista> recordList = new ArrayList<>();
            recordList.add(new RecordEntrevista("Entrevista 1", "Descripción de la entrevista 1"));
            recordList.add(new RecordEntrevista("Entrevista 2", "Descripción de la entrevista 2"));

            // Configura el adaptador del RecyclerView para RecordEntrevista
            RecordEntrevistaAdapter recordEntrevistaAdapter = new RecordEntrevistaAdapter(recordList);
            recyclerViewRecordEntrevista.setAdapter(recordEntrevistaAdapter);

        } else {
            textView.setText("Bienvenido al Home");
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}