package com.example.inmobiliarianovara.ui.Perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliarianovara.R;
import com.example.inmobiliarianovara.modelo.Propietario;



public class PerfilFragment extends Fragment {

    private EditText etDni,etNombre,etApellido,etTelefono,etContraseña,etEmail;
    private Button btnEditar;
    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        View root = inflater.inflate(R.layout.fragment_perfil,container,false);
        inicializarVista(root);

        perfilViewModel.getUsuario().observe(getViewLifecycleOwner(),new Observer<Propietario>()
            {
                @Override
                public void onChanged(Propietario propietario) {
                    etDni.setText(propietario.getDni().toString());
                    etNombre.setText(propietario.getNombre());
                    etApellido.setText(propietario.getApellido());
                    etTelefono.setText(propietario.getTelefono());
                    etContraseña.setText(propietario.getContraseña());
                    etEmail.setText(propietario.getEmail());
                }
            }
        );
        perfilViewModel.getEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etDni.setEnabled(aBoolean);
                etNombre.setEnabled(aBoolean);
                etApellido.setEnabled(aBoolean);
                etTelefono.setEnabled(aBoolean);
                etContraseña.setEnabled(aBoolean);
                etEmail.setEnabled(aBoolean);
            }
        });

        perfilViewModel.getTextoBoton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btnEditar.setText(s);
            }
        });

        perfilViewModel.traerDatos();

        return root;
    }

    private void inicializarVista(View root)
    {
        etDni = root.findViewById(R.id.etCodigoInquilino);
        etNombre = root.findViewById(R.id.etNombreInquilino);
        etApellido = root.findViewById(R.id.etApellidoInquilino);
        etTelefono = root.findViewById(R.id.etTelefonoInquilino);
        etContraseña = root.findViewById(R.id.etEmailInquilino);
        etEmail = root.findViewById(R.id.etDni);

        btnEditar = root.findViewById(R.id.btnPagos);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario propietario = new Propietario();
                propietario.setDni(Long.parseLong(etDni.getText().toString()));
                propietario.setNombre(etNombre.getText().toString());
                propietario.setApellido(etApellido.getText().toString());
                propietario.setTelefono(etTelefono.getText().toString());
                propietario.setContraseña(etContraseña.getText().toString());
                propietario.setEmail(etEmail.getText().toString());

                String texto = btnEditar.getText().toString();

                perfilViewModel.accionBoton(texto,propietario);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}