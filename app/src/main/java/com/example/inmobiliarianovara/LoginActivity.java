package com.example.inmobiliarianovara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText etUsuario,etContraseña;
    private LoginViewModel lv;
    private SensorManager sensorManager;
    private LeerSensor leerSensor;
    private Llamada llamada;
    private int bandera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }

        ObtenerSensores();

        InicializarVista();

        lv.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InicializarVista()
    {
        login=findViewById(R.id.btnIniciar);
        etUsuario=findViewById(R.id.etUsuario);
        etContraseña=findViewById(R.id.etInquilino);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.iniciarSesion(etUsuario.getText().toString(),etContraseña.getText().toString());
            }
        });
    }

    public void ObtenerSensores()
    {
        leerSensor = new LeerSensor();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    }

    public class Llamada extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }

    public class LeerSensor implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(bandera == 0 && sensorEvent.values[0] >= 20 || sensorEvent.values[0] <= -20)
            {
                Uri tel = Uri.parse("tel:2664110272");
                startActivity(new Intent(Intent.ACTION_CALL,tel));
                bandera=1;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }




}