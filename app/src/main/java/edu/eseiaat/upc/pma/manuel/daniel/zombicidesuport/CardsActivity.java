package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class CardsActivity extends AppCompatActivity {
    public static String Keycarta1="key_carta1";

    private ArrayList<Drawable> listacartas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        listacartas=new ArrayList<>();
        Todas();

        /*Intent intent = getIntent();
        int a = intent.getExtras().getInt(Keycarta1);
        ImageView icono = (ImageView) findViewById(R.id.Carta1);
        icono.setImageDrawable(getDrawable(R.drawable.calavera));*/
    }

    private void Todas() {
        listacartas.clear();
        listacartas.add(getDrawable(R.drawable.cbagofrice));
        listacartas.add(getDrawable(R.drawable.cbagofrice));

    }
}
