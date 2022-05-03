package com.example.inmobiliarianovara.ui.Inquilino;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliarianovara.R;
import com.example.inmobiliarianovara.modelo.Inmueble;

import java.util.ArrayList;

public class InmueblesAlquiladosFragment extends Fragment {
    private RecyclerView rvInmueblesAlquilados;
    private InmueblesAlquiladosViewModel inmueblesAlquiladosViewModel;
    Inmueble_alquilados_Adapter adapter;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inmuebles_alquilados, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View view) {
        rvInmueblesAlquilados = view.findViewById(R.id.rvInmueblesAlquilados);
        inmueblesAlquiladosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmueblesAlquiladosViewModel.class);
        inmueblesAlquiladosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager= new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
                rvInmueblesAlquilados.setLayoutManager(gridLayoutManager);
                adapter = new Inmueble_alquilados_Adapter(context, inmuebles, getLayoutInflater());
                rvInmueblesAlquilados.setAdapter(adapter);
            }
        });
        inmueblesAlquiladosViewModel.cargarInmueblesAlquilados();
    }

}