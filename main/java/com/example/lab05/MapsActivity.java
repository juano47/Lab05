package com.example.lab05;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng latLng2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mover camara a Santa Fe y acercar
        LatLng santaFe = new LatLng(-31.6354434, -60.7063567);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(santaFe));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(12));

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //agregamos un marcador donde el usuario haga un click prolongado
                mMap.addMarker(new MarkerOptions().position(latLng));
                latLng2 = latLng;

            }
        });
for (int i=0; i<MainActivity._PLATOS.size(); i++){
    mMap.addMarker(new MarkerOptions()
            .position(new LatLng(MainActivity._PLATOS.get(i).getPrecio(), MainActivity._PLATOS.get(i).getCalorias()))
            .title(MainActivity._PLATOS.get(i).getTitulo()));
}


        Button botonAceptarCoordenadas = (Button) findViewById(R.id.bottomAceptarCoordenadasEnMapa);
        botonAceptarCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResultado = new Intent();
                intentResultado.putExtra("latitud", latLng2.latitude);
                intentResultado.putExtra("longitud", latLng2.longitude);
                setResult(Activity.RESULT_OK, intentResultado);
                finish();
            }
        });


    }
}
