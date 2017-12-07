package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity {


    public static String KeyListaPersonajes="key_listaPersonajes";
    private ArrayList<Personaje> listaPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        Bundle bundle = getIntent().getExtras();
        listaPersonajes = bundle.getParcelableArrayList(KeyListaPersonajes);
    }
}
