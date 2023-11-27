package com.uniandes.abcjobsgrp23.ui.dashboard;

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
import com.uniandes.abcjobsgrp23.data.model.TecnicalInterview;
import com.uniandes.abcjobsgrp23.databinding.FragmentDashboardBinding;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.view.pruebaDesempeno.CrearPruebaDesempenoActivity;
import com.uniandes.abcjobsgrp23.view.pruebaTecnica.CrearTecnicalInterviewActivity;
import com.uniandes.abcjobsgrp23.view.pruebaTecnica.RecordTecnicalInterviewAdapter;
import com.uniandes.abcjobsgrp23.viewmodel.TecnicalInterviewViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private TecnicalInterviewViewModel tecnicalInterviewViewModel;
    private List<TecnicalInterview> recordList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ImageButton btnTecnicalInterview = root.findViewById(R.id.btnAddTecnicalInterview);

        // Obtiene el tipo de usuario
        String  userType = UserType.getUserType();

        // Muestra contenido en función del tipo de usuario
        if (userType == UserType.CANDIDATO) {
            textView.setText("P.Tecnica, Candidato.");
        } else if (userType == UserType.EMPRESA) {
            textView.setText("P.Tecnica, Empresa.");

            // Inicializa y configura el RecyclerView para RecordEntrevista
            RecyclerView recyclerViewRecordTecnicalInterview = root.findViewById(R.id.recyclerViewRecordTecnicalInterview);
            recyclerViewRecordTecnicalInterview.setLayoutManager(new LinearLayoutManager(requireContext()));

            // Configura el adaptador del RecyclerView para RecordEntrevista
            RecordTecnicalInterviewAdapter recordTecnicalInterviewAdapter = new RecordTecnicalInterviewAdapter(recordList);
            recyclerViewRecordTecnicalInterview.setAdapter(recordTecnicalInterviewAdapter);

            // Inicializa el ViewModel
            tecnicalInterviewViewModel = new ViewModelProvider(this).get(TecnicalInterviewViewModel.class);

            tecnicalInterviewViewModel.getAllTecnicalInterviews().observe(getViewLifecycleOwner(), entrevistas -> {
                // Actualiza la lista recordList con las nuevas entrevistas
                recordList.clear();
                recordList.addAll(entrevistas);
                // Notifica al adaptador de que los datos han cambiado
                recordTecnicalInterviewAdapter.notifyDataSetChanged();
            });

            btnTecnicalInterview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CrearTecnicalInterviewActivity.class);
                    // Inicia la nueva actividad
                    v.getContext().startActivity(intent);

                }
            });

        } else {
            textView.setText("Bienvenido al Dashboard.");
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}