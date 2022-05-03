package com.example.inmobiliarianovara.ui.Contratos;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;


import com.example.inmobiliarianovara.R;
import com.example.inmobiliarianovara.modelo.Contrato;
import com.example.inmobiliarianovara.modelo.Inmueble;
import com.example.inmobiliarianovara.modelo.Inquilino;
import com.example.inmobiliarianovara.request.ApiClient;

import java.util.Calendar;

public class ContratoViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Contrato> contrato;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getContrato() {
        if (contrato == null) {
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void cargarContrato(Bundle bundle) {
        //Acá recibiríamos un inmueble o Id de inmueble, y buscaríamos en la base de datos el contrato vigente de ese inmueble
        //En caso de no existir, la vista mostraría un mensaje (Si partimos de la pestaña de contratos se supone que el inmueble tiene un contrato vigente)
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient apiClient=ApiClient.getApi();
        Contrato contrato=apiClient.obtenerContratoVigente(inmueble);

        this.contrato.setValue(contrato);
    }


}