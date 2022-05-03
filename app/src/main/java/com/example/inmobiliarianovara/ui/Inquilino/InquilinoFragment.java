package com.example.inmobiliarianovara.ui.Inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliarianovara.R;
import com.example.inmobiliarianovara.modelo.Inmueble;
import com.example.inmobiliarianovara.modelo.Inquilino;
import com.example.inmobiliarianovara.ui.Inmuebles.InmuebleViewModel;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel inquilinoViewModel;
    private EditText etCodigo,etNombre,etApellido,etDni,etEmail,etTelefonoInquilino,etGarante,etTelefonoGarante;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinoViewModel slideshowViewModel =
                new ViewModelProvider(this).get(InquilinoViewModel.class);

        View root = inflater.inflate(R.layout.fragment_inquilino,container,false);
        InicializarVista(root);

        return root;
    }

    public void InicializarVista(View view)
    {
        etCodigo = view.findViewById(R.id.etCodigoInquilino);
        etNombre = view.findViewById(R.id.etNombreInquilino);
        etApellido = view.findViewById(R.id.etApellidoInquilino);
        etDni = view.findViewById(R.id.etDni);
        etEmail = view.findViewById(R.id.etEmailInquilino);
        etTelefonoInquilino = view.findViewById(R.id.etTelefonoInquilino);
        etGarante = view.findViewById(R.id.etNombreGarante);
        etTelefonoGarante = view.findViewById(R.id.etTelefonoGarante);

        inquilinoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);
        inquilinoViewModel.getInquilino().observe(getActivity(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                etCodigo.setText(inquilino.getIdInquilino() + " ");
                etNombre.setText(inquilino.getNombre());
                etApellido.setText(inquilino.getApellido());
                etDni.setText(inquilino.getDNI() + " ");
                etEmail.setText(inquilino.getEmail());
                etTelefonoInquilino.setText(inquilino.getTelefono());
                etGarante.setText(inquilino.getNombreGarante());
                etTelefonoGarante.setText(inquilino.getTelefonoGarante());
            };

        });
        inquilinoViewModel.cargarInquilino(getArguments());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}