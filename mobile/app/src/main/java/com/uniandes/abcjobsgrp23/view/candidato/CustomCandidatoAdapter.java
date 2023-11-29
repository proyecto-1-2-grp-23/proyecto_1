package com.uniandes.abcjobsgrp23.view.candidato;


import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.uniandes.abcjobsgrp23.data.model.Candidato;

import java.util.List;

public class CustomCandidatoAdapter extends ArrayAdapter<Candidato> {

    public CustomCandidatoAdapter(Context context, List<Candidato> candidatos) {
        super(context, 0, candidatos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        Candidato candidato = getItem(position);

        // Aquí defines cómo quieres mostrar la información en el Spinner
        if (candidato != null) {
            String displayText = candidato.getId() + " " + candidato.getNombreCompleto(); // Aquí concatenas los atributos deseados
            textView.setText(displayText);
        }

        return convertView;
    }
}

