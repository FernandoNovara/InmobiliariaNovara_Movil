package com.example.inmobiliarianovara;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliarianovara.modelo.Propietario;
import com.example.inmobiliarianovara.request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mensaje;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<String> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    public void iniciarSesion(String usuario,String Contraseña)
    {
        ApiClient api = ApiClient.getApi();
        Propietario propietarioLogueado= api.login(usuario,Contraseña);
        if(propietarioLogueado != null)
        {
            Intent i = new Intent(context,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        else
        {
            mensaje.setValue("Usuario y/o Contraseña incorrecta.");
        }
    }
}
