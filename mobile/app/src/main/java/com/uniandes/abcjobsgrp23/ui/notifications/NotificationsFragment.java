package com.uniandes.abcjobsgrp23.ui.notifications;

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

import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.databinding.FragmentNotificationsBinding;
import com.uniandes.abcjobsgrp23.ui.auth.UserType;
import com.uniandes.abcjobsgrp23.view.pruebaDesempeno.CrearPruebaDesempenoActivity;
import com.uniandes.abcjobsgrp23.view.pruebaTecnica.CrearTecnicalInterviewActivity;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton btnPruebaDesempeno = root.findViewById(R.id.btnAddPruebaDesempeno);

        final TextView textView = binding.textNotifications;
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Obtiene el tipo de usuario
        String  userType = UserType.getUserType();

        // Muestra contenido en función del tipo de usuario
        if (userType == UserType.CANDIDATO) {
            textView.setText("P. Desempeño, Candidato.");
        } else if (userType == UserType.EMPRESA) {
            textView.setText("P. Desempeño, Empresa.");

            btnPruebaDesempeno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CrearPruebaDesempenoActivity.class);
                    // Inicia la nueva actividad
                    v.getContext().startActivity(intent);
                }
            });


        } else {
            textView.setText("Bienvenido a P. Desempeño");
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}