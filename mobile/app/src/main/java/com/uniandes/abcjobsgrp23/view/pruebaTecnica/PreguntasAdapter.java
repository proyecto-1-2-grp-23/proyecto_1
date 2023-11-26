package com.uniandes.abcjobsgrp23.view.pruebaTecnica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniandes.abcjobsgrp23.R;
import com.uniandes.abcjobsgrp23.data.model.Pregunta;

import java.util.List;

public class PreguntasAdapter extends RecyclerView.Adapter<PreguntasAdapter.PreguntaViewHolder> {

    private List<Pregunta> preguntas;

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PreguntaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pregunta_calificacion, parent, false);
        return new PreguntaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreguntaViewHolder holder, int position) {
        if (preguntas != null) {
            Pregunta pregunta = preguntas.get(position);
            holder.bind(pregunta);
        }
    }

    @Override
    public int getItemCount() {
        return preguntas != null ? preguntas.size() : 0;
    }

    static class PreguntaViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPregunta;

        public PreguntaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPregunta = itemView.findViewById(R.id.textViewEnunciado);
        }

        public void bind(Pregunta pregunta) {
            textViewPregunta.setText(pregunta.getPregunta());
        }
    }
}
