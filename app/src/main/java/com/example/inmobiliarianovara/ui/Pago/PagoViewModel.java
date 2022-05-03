package com.example.inmobiliarianovara.ui.Pago;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.inmobiliarianovara.modelo.Contrato;
import com.example.inmobiliarianovara.modelo.Pago;
import com.example.inmobiliarianovara.request.ApiClient;
import java.util.ArrayList;

public class PagoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Pago>> pagos;

    public LiveData<ArrayList<Pago>> getPagos () {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }
    public void cargarPagos(Bundle bundle) {
        Contrato contrato = (Contrato) bundle.get("contrato");
        ApiClient apiClient=ApiClient.getApi();
        ArrayList<Pago> pagos = apiClient.obtenerPagos(contrato);
        this.pagos.setValue(pagos);
    }

}