package com.example.inmobiliarianovara.ui.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliarianovara.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mapa;
    private LatLng Inmobiliaria = new LatLng(-33.051028,-65.627130);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioViewModel homeViewModel =
                new ViewModelProvider(this).get(InicioViewModel.class);

        View root = inflater.inflate(R.layout.fragment_inicio,container,false);

        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frMap);
        smf.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        mapa = googleMap;
        CameraPosition camPos = new CameraPosition.Builder()
                .target(Inmobiliaria)
                .zoom(17)
                .build();

        CameraUpdate camUpdICT = CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpdICT);

        mapa.addMarker(new MarkerOptions().position(Inmobiliaria)).setTitle("Inmobiliaria Novara");
        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mapa.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                mapa.addMarker(new MarkerOptions().position(latLng)).setTitle("marca");
            }
        });
    }

}

