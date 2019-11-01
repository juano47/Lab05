package com.example.lab05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Plato> _PLATOS = new ArrayList<>();
    public static Integer i = 1;


    private Context context;
    private static final int CODIGO_SELECCIONAR_COORDENADAS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        Plato plato = new Plato(1, "Plato 1", "descripcion", 22.50, 2.0, false);
        _PLATOS.add(plato);
        plato = new Plato(2, "Plato 2", "descripcion", 120.00, 2.0, false);
        _PLATOS.add(plato);
        plato = new Plato(3, "Plato 3", "descripcion", 520.55, 2.0, false);
        _PLATOS.add(plato);
        plato = new Plato(3, "Plato 4", "descripcion", 70.40, 2.0, false);
        _PLATOS.add(plato);
        plato = new Plato(3, "Plato 5", "descripcion", 220.90, 2.0, false);
        _PLATOS.add(plato);
        plato = new Plato(1, "Plato 6", "descripcion", 55.00, 2.0, false);
        _PLATOS.add(plato);

        Button botonIrmapa = (Button) findViewById(R.id.buttonSeleccionarLtLn);
        botonIrmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,MapsActivity.class);
                startActivityForResult(i, CODIGO_SELECCIONAR_COORDENADAS);
            }
        });

        Button botonMostrarPlatosEnMapa = (Button) findViewById(R.id.buttonMostrarPlatosEnMapa);
        botonMostrarPlatosEnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,MapsActivity.class);

                startActivity(i);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Nos fijamos de que actividad viene el resultado
        if (requestCode == CODIGO_SELECCIONAR_COORDENADAS) {
            // Verificamos que el request tuvo éxito
            if (resultCode == RESULT_OK) {
                //obtenemos las coordenadas seleccionadas
                Double latitud = data.getDoubleExtra("latitud",0);
                Double longitud = data.getDoubleExtra("longitud", 0);
                //Mostramos las coordenadas seleccionadas
                TextView coordenadas = (TextView) findViewById(R.id.textViewMostrarCoordenadas);
                coordenadas.setText("Coordenadas seleccionadas: \n Latitud: " + latitud.toString()+ "\n Longitud: " + longitud.toString() );
                Plato plato = _PLATOS.get(i);
                plato.setPrecio(latitud);
                plato.setCalorias(longitud);
                _PLATOS.remove(i);
                _PLATOS.add(plato);
                i++;
            }
        }
    }
}
