package com.example.inmobiliarianovara.ui.Contratos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import com.example.inmobiliarianovara.R;
import com.example.inmobiliarianovara.modelo.Contrato;

import java.text.SimpleDateFormat;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private TextView tvCodigoContrato;
    private TextView tvFechaInicio;
    private TextView tvFechaFin;
    private TextView tvMontoAlquiler;
    private TextView tvInquilino;
    private TextView tvInmueble;
    private Button btPagos;
    private Context context;
    private Contrato contratoVer;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.detalle_contrato_fragment, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View view) {
        tvCodigoContrato = view.findViewById(R.id.tvCodigoContrato);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvFechaFin = view.findViewById(R.id.tvFechaFinal);
        tvMontoAlquiler = view.findViewById(R.id.tvMontoAlquiler);
        tvInquilino = view.findViewById(R.id.tvInquilino);
        tvInmueble = view.findViewById(R.id.tvInmueble);
        btPagos=view.findViewById(R.id.btnPagos);
        btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", contratoVer);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.pagoFragment, bundle);
            }
        });

        contratoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);
        contratoViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                contratoVer=contrato;
                tvCodigoContrato.setText(contrato.getIdContrato() + "");
                tvFechaInicio.setText(contrato.getFechaInicio());
                tvFechaFin.setText(contrato.getFechaFin());
                tvMontoAlquiler.setText("$" + contrato.getMontoAlquiler());
                tvInquilino.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
                tvInmueble.setText("Inmueble en " + contrato.getInmueble().getDireccion());
            }
        });
        contratoViewModel.cargarContrato(getArguments());


    }

}