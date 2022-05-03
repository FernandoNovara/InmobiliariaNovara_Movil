package com.example.inmobiliarianovara.ui.Logout;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliarianovara.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.inmobiliarianovara.R;
import com.example.inmobiliarianovara.ui.Perfil.PerfilViewModel;

import org.w3c.dom.Text;

public class LogoutFragment extends Fragment {

    private TextView tvlogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LogoutViewModel galleryViewModel =
                new ViewModelProvider(this).get(LogoutViewModel.class);

        View root = inflater.inflate(R.layout.fragment_logoout, container, false);
        mostrarDialog();
        tvlogout = root.findViewById(R.id.text_logout);
        tvlogout.setText("Este es el fragment Logout");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public void mostrarDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getContext())
                .setTitle("Titulo")
                .setMessage("Desea cerrar la app?")
                .setPositiveButton (R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main).navigate(R.id.nav_inicio);
                    }
                });
        alertDialog.show();
    }
}