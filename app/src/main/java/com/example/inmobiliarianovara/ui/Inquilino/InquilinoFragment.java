package com.example.inmobiliarianovara.ui.Inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliarianovara.R;

public class InquilinoFragment extends Fragment {

    private TextView tvinquilino;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinoViewModel slideshowViewModel =
                new ViewModelProvider(this).get(InquilinoViewModel.class);

        View root = inflater.inflate(R.layout.fragment_inquilino,container,false);
        tvinquilino = root.findViewById(R.id.text_inquilino);
        tvinquilino.setText("Este es el fragment Inquilinos");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}