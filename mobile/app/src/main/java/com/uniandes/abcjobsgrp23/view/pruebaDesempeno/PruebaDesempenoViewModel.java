package com.uniandes.abcjobsgrp23.view.pruebaDesempeno;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uniandes.abcjobsgrp23.data.model.ApiResponse;
import com.uniandes.abcjobsgrp23.data.model.HabilidadPuntaje;
import com.uniandes.abcjobsgrp23.data.model.ResultadosDesempeno;
import com.uniandes.abcjobsgrp23.data.service.RetrofitBroker;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PruebaDesempenoViewModel extends ViewModel {
    private MutableLiveData<Boolean> pruebaDesempenoGuardada = new MutableLiveData<>();

    public LiveData<Boolean> getPruebaDesempenoGuardada() {
        return pruebaDesempenoGuardada;
    }

    public void guardarPruebaDesempeno(int candidatoId, ResultadosDesempeno resultadosDesempeno) {
        List<HabilidadPuntaje> habilidades = resultadosDesempeno.getHabilidades();
        int totalHabilidades = habilidades.size();
        AtomicInteger habilidadesCompletadas = new AtomicInteger(0);

        for (HabilidadPuntaje habilidad : habilidades) {
            RetrofitBroker.enviarPruebaDesempeno(
                    candidatoId,
                    resultadosDesempeno.getIdPrueba(),
                    resultadosDesempeno.getIdEmpresa(),
                    habilidad.getHabilidad(),
                    habilidad.getPuntaje(),
                    new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()) {
                                // Incrementar el contador de habilidades completadas
                                habilidadesCompletadas.incrementAndGet();
                            } else {
                                // Tratar el fallo de la petición
                            }

                            // Verificar si se han completado todas las habilidades
                            if (habilidadesCompletadas.get() == totalHabilidades) {
                                // Notificar el resultado solo cuando todas las habilidades estén completadas
                                if (habilidadesCompletadas.get() == totalHabilidades) {
                                    if (response.isSuccessful()) {
                                        pruebaDesempenoGuardada.postValue(true);
                                    } else {
                                        pruebaDesempenoGuardada.postValue(false);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            // Tratar el fallo de la petición
                            // Incrementar el contador de habilidades completadas
                            habilidadesCompletadas.incrementAndGet();

                            // Verificar si se han completado todas las habilidades
                            if (habilidadesCompletadas.get() == totalHabilidades) {
                                // Notificar el resultado solo cuando todas las habilidades estén completadas
                                pruebaDesempenoGuardada.postValue(false);
                            }
                        }
                    }
            );
        }
    }
}
