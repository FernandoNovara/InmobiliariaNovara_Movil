package com.example.inmobiliarianovara.ui.Contratos;

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

public class ContratosFragment extends Fragment {

    private ContratosViewModel contratosViewModel;
    private RecyclerView rvInmuebles;
    private InmuebleConContratoAdapter adapter;
    Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contratos, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }



    private void inicializar(View view) {
        contratosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication()).create(ContratosViewModel.class);
        rvInmuebles = view.findViewById(R.id.rvInmuebles);
        contratosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                adapter = new InmuebleConContratoAdapter(context, inmuebles, getLayoutInflater());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);
                rvInmuebles.setLayoutManager(gridLayoutManager);
                rvInmuebles.setAdapter(adapter);
            }
        });
        contratosViewModel.cargarInmueblesConContrato();
    }




}