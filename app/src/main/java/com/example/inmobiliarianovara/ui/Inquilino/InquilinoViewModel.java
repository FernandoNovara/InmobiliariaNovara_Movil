package com.example.inmobiliarianovara.ui.Inquilino;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliarianovara.modelo.Inmueble;
import com.example.inmobiliarianovara.modelo.Inquilino;
import com.example.inmobiliarianovara.request.ApiClient;

public class InquilinoViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilino;
    public InquilinoViewModel() {
        super();
    }
    public LiveData<Inquilino> getInquilino() {
        if (inquilino == null) {
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void cargarInquilino(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        Inquilino inquilino= ApiClient.getApi().obtenerInquilino(inmueble);
        this.inquilino.setValue(inquilino);

    }
}