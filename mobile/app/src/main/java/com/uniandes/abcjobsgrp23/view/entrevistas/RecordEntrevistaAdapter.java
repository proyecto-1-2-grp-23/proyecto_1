package com.uniandes.abcjobsgrp23.view.entrevistas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.RecordEntrevista;
import java.util.List;
public class RecordEntrevistaAdapter extends RecyclerView.Adapter<RecordEntrevistaAdapter.RecordEntrevistaViewHolder> {
    private List<RecordEntrevista> recordList;

    public RecordEntrevistaAdapter(List<RecordEntrevista> recordList) {
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public RecordEntrevistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_entrevista, parent, false);
        return new RecordEntrevistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordEntrevistaViewHolder holder, int position) {
        RecordEntrevista record = recordList.get(position);
        holder.titleTextView.setText(record.getTitle());
        holder.descriptionTextView.setText(record.getDescription());

        // Manejador de eventos para el botón "Ver Detalle"
        holder.btnVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el registro en la posición 'position'
                RecordEntrevista selectedRecord = recordList.get(position);

                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), DetalleEntrevistaCandidatoActivity.class);

                // Pasa los datos necesarios a través del Intent
                intent.putExtra("titulo", selectedRecord.getTitle());
                intent.putExtra("descripcion", selectedRecord.getDescription());
                intent.putExtra("Editar", false);

                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });

        // Manejador de eventos para el botón "Editar"
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el registro en la posición 'position'
                RecordEntrevista selectedRecord = recordList.get(position);

                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), DetalleEntrevistaCandidatoActivity.class);
                intent.putExtra("Editar", true);

                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });

        // Manejador de eventos para el botón "Ver Accion"
        holder.btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el registro en la posición 'position'
                RecordEntrevista selectedRecord = recordList.get(position);
                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), ResultadoEntrevistaActivity.class);
                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return recordList.size();
    }

    static class RecordEntrevistaViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageButton btnVerDetalle;
        ImageButton  btnEditar;
        ImageButton  btnResultado;

        RecordEntrevistaViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewDetEntreTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDetEntreDescription);
            btnVerDetalle = itemView.findViewById(R.id.btnVerDetalle);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnResultado = itemView.findViewById(R.id.btnVerResultado);
        }
    }

}
