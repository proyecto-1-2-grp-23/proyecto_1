package com.uniandes.abcjobsgrp23.view.entrevistas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.Entrevista;

import java.util.List;
public class RecordEntrevistaAdapter extends RecyclerView.Adapter<RecordEntrevistaAdapter.RecordEntrevistaViewHolder> {
    private List<Entrevista> entrevistas;

    public RecordEntrevistaAdapter(List<Entrevista> entrevistas) {
        this.entrevistas = entrevistas;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setEntrevistas(List<Entrevista> entrevistas) {
        this.entrevistas = entrevistas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordEntrevistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_entrevista, parent, false);
        return new RecordEntrevistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordEntrevistaViewHolder holder, int position) {
        Entrevista record = entrevistas.get(position);
        holder.titleTextView.setText(record.getCandidato().getNombreCompleto());
        holder.descriptionTextView.setText(record.getEmpresa().getRazonSocial());

        // Manejador de eventos para el botón "Ver Detalle"
        holder.btnVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el registro en la posición 'position'
                Entrevista selectedRecord = entrevistas.get(position);

                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), DetalleEntrevistaCandidatoActivity.class);

                // Pasa los datos necesarios a través del Intent
                intent.putExtra("titulo", selectedRecord.getCandidato().getNombreCompleto());
                intent.putExtra("nombreEmpresa", selectedRecord.getEmpresa().getRazonSocial());
                intent.putExtra("nombreCandidato", selectedRecord.getCandidato().getNombreCompleto());
                intent.putExtra("correoCandidato", selectedRecord.getCandidato().getCorreo());
                intent.putExtra("fechaEntrevista", selectedRecord.getFecha());
                intent.putExtra("lugarEntrevista", selectedRecord.getLugar());
                intent.putExtra("Editar", false);

                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });

        // Manejador de eventos para el botón "Editar"
//        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Obtén el registro en la posición 'position'
//                Entrevista selectedRecord = entrevistas.get(position);
//
//                // Crea un Intent para abrir la nueva actividad
//                Intent intent = new Intent(v.getContext(), DetalleEntrevistaCandidatoActivity.class);
//                intent.putExtra("Editar", true);
//
//                // Inicia la nueva actividad
//                v.getContext().startActivity(intent);
//            }
//        });

        // Manejador de eventos para el botón "Agregar Entrevista"
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el registro en la posición 'position'
                Entrevista selectedRecord = entrevistas.get(position);

                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), DetalleEntrevistaCandidatoActivity.class);
                intent.putExtra("Editar", true);

                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });

        // Manejador de eventos para el botón "Agregar Resultado"
        holder.btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el registro en la posición 'position'
                Entrevista selectedRecord = entrevistas.get(position);
                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), ResultadoEntrevistaActivity.class);
                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });



    }
    @Override
    public int getItemCount() {
        return entrevistas.size();
    }

    static class RecordEntrevistaViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageButton btnVerDetalle;
        ImageButton  btnEditar;
        ImageButton  btnResultado;
        ImageButton  btnAgregar;

        RecordEntrevistaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewDetEntreTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDetEntreDescription);
            btnVerDetalle = itemView.findViewById(R.id.btnVerDetalle);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnResultado = itemView.findViewById(R.id.btnVerResultado);
//            btnAgregar = itemView.findViewById(R.id.btnAddInterview);

        }
    }

}
