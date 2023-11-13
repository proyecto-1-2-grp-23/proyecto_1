package com.uniandes.abcjobsgrp23.view.pruebaTecnica;

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
import com.uniandes.abcjobsgrp23.data.model.TecnicalInterview;

import java.util.List;

public class RecordTecnicalInterviewAdapter extends RecyclerView.Adapter<RecordTecnicalInterviewAdapter.RecordTecnicalInterviewViewHolder> {
    private List<TecnicalInterview> tecnicalInterviews;

    public RecordTecnicalInterviewAdapter(List<TecnicalInterview> tecnicalInterviews) {
        this.tecnicalInterviews = tecnicalInterviews;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTecnicalInterview(List<TecnicalInterview> tecnicalInterviews) {
        this.tecnicalInterviews = tecnicalInterviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordTecnicalInterviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tecnical_interview, parent, false);
        return new RecordTecnicalInterviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordTecnicalInterviewViewHolder holder, int position) {
        TecnicalInterview record = tecnicalInterviews.get(position);
        holder.titleTextView.setText(record.getCandidato().getNombreCompleto());
        holder.descriptionTextView.setText(record.getProyecto().getNombre());

        // Manejador de eventos para el botón "Ver Detalle"
        holder.btnVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el registro en la posición 'position'
                TecnicalInterview selectedRecord = tecnicalInterviews.get(position);

                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(v.getContext(), DetalleTecnicalInterviewActivity.class);

                // Pasa los datos necesarios a través del Intent
                intent.putExtra("titulo", selectedRecord.getCandidato().getNombreCompleto());
                intent.putExtra("nombreProyecto", selectedRecord.getProyecto().getNombre());
                intent.putExtra("nombreCandidato", selectedRecord.getCandidato().getNombreCompleto());
                intent.putExtra("correoCandidato", selectedRecord.getCandidato().getCorreo());
                intent.putExtra("Editar", false);

                // Inicia la nueva actividad
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tecnicalInterviews.size();
    }

    static class RecordTecnicalInterviewViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageButton btnVerDetalle;
        ImageButton btnResultado;

        RecordTecnicalInterviewViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewDetEntreTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDetEntreDescription);
            btnVerDetalle = itemView.findViewById(R.id.btnVerDetalleTecnicalInterview);
            btnResultado = itemView.findViewById(R.id.btnVerResultadoTecnicalInterview);
        }
    }
}
