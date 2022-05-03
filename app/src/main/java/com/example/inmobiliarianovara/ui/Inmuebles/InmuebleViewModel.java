package com.example.inmobiliarianovara.ui.Inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliarianovara.modelo.Inmueble;
import com.example.inmobiliarianovara.request.ApiClient;


public class InmuebleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmueble;
    public InmuebleViewModel() {
        super();
    }
    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void cargarInmueble(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        this.inmueble.setValue(inmueble);
    }
}











