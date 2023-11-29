package com.uniandes.abcjobsgrp23.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;
import com.uniandes.abcjobsgrp23.data.model.RecordEntrevista;
import com.uniandes.abcjobsgrp23.databinding.FragmentHomeBinding;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.view.entrevistas.CreateEntrevistaCandidatoActivity;
import com.uniandes.abcjobsgrp23.view.entrevistas.DetalleEntrevistaCandidatoActivity;
import com.uniandes.abcjobsgrp23.view.entrevistas.RecordEntrevistaAdapter;
import com.uniandes.abcjobsgrp23.view.pruebaTecnica.CrearTecnicalInterviewActivity;
import com.uniandes.abcjobsgrp23.viewmodel.EntrevistaViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private EntrevistaViewModel entrevistaViewModel;
    private List<Entrevista> recordList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton btnAddInterview = root.findViewById(R.id.btnAddInterview);

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

            // Configura el adaptador del RecyclerView para RecordEntrevista
            RecordEntrevistaAdapter recordEntrevistaAdapter = new RecordEntrevistaAdapter(recordList);
            recyclerViewRecordEntrevista.setAdapter(recordEntrevistaAdapter);

            // Inicializa el ViewModel
            entrevistaViewModel = new ViewModelProvider(this).get(EntrevistaViewModel.class);

            entrevistaViewModel.getAllEntrevistas().observe(getViewLifecycleOwner(), entrevistas -> {
                // Actualiza la lista recordList con las nuevas entrevistas
                recordList.clear();
                recordList.addAll(entrevistas);
                // Notifica al adaptador de que los datos han cambiado
                recordEntrevistaAdapter.notifyDataSetChanged();
            });

            btnAddInterview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CreateEntrevistaCandidatoActivity.class);
                    intent.putExtra("Editar", true);
                    // Inicia la nueva actividad
                    v.getContext().startActivity(intent);
                }
            });

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