package com.uniandes.abcjobsgrp23.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.uniandes.abcjobsgrp23.databinding.FragmentDashboardBinding;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Obtiene el tipo de usuario
        String  userType = UserType.getUserType();

        // Muestra contenido en función del tipo de usuario
        if (userType == UserType.CANDIDATO) {
            textView.setText("Dashboard, Candidato.");
        } else if (userType == UserType.EMPRESA) {
            textView.setText("Dashboard, Empresa.");
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