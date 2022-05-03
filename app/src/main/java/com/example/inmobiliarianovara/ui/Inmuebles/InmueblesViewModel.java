package com.example.inmobiliarianovara.ui.Inmuebles;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.inmobiliarianovara.R;
import com.example.inmobiliarianovara.modelo.Inmueble;
import com.example.inmobiliarianovara.request.ApiClient;

import java.util.ArrayList;

public class InmueblesViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;
    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmuebles == null) {
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void cargarInmuebles() {
        ApiClient api=ApiClient.getApi();
        ArrayList<Inmueble> inmuebles=api.obtenerPropiedades();
        this.inmuebles.setValue(inmuebles);

    }

}