package com.example.inmobiliarianovara.ui.Contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.inmobiliarianovara.modelo.Inmueble;
import com.example.inmobiliarianovara.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;
    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmuebles == null) {
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void cargarInmueblesConContrato() {
        //Acá traemos los inmuebles que tienen contratos vigentes en la base de datos
        ApiClient apiClient=ApiClient.getApi();
        ArrayList<Inmueble> inmuebles=apiClient.obtenerPropiedadesAlquiladas();
        this.inmuebles.setValue(inmuebles);
    }
}
