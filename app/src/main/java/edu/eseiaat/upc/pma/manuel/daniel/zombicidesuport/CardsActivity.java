package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class CardsActivity extends AppCompatActivity {
    public static String Keycarta1="key_carta1";

    private ArrayList<Cartas> listacartas;
    private CartasAdapter adaptercartas;
    private RecyclerView viewCartas;
    private LinearLayoutManager linlayoutmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        listacartas=new ArrayList<>();
        Crear();
        viewCartas=(RecyclerView)findViewById(R.id.ViewCartas);
        linlayoutmanager =new LinearLayoutManager(this);
        viewCartas.setLayoutManager(linlayoutmanager);
        adaptercartas =new CartasAdapter(this,listacartas);
        viewCartas.setAdapter(adaptercartas);

        /*Intent intent = getIntent();
        int a = intent.getExtras().getInt(Keycarta1);
        ImageView icono = (ImageView) findViewById(R.id.Carta1);
        icono.setImageDrawable(getDrawable(R.drawable.calavera));*/
    }

    private void Crear() {
        OtrosCartas();
        EspecialesCartas();
        CuerpoCartas();
        DistanciaCartas();
    }

    private void DistanciaCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cmashotgun)));
        listacartas.add(new Cartas(getDrawable(R.drawable.ceviltwins)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cpistol)));
        listacartas.add(new Cartas(getDrawable(R.drawable.crifle)));
        listacartas.add(new Cartas(getDrawable(R.drawable.csawedoff)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cshotgun)));
        listacartas.add(new Cartas(getDrawable(R.drawable.csubmg)));
    }

    private void CuerpoCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cbaseballbat)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cchainsaw)));
        listacartas.add(new Cartas(getDrawable(R.drawable.ccrowbar)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cfireaxe)));
        listacartas.add(new Cartas(getDrawable(R.drawable.ckatana)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmachete)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cpan)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmashotgun)));
    }

    private void EspecialesCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cgoaliemask)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cflashlight)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cplentyofammo)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cplentyofammoshotgun)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cscope)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmolotov)));
    }

    private void OtrosCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cbagofrice)));
        listacartas.add(new Cartas(getDrawable(R.drawable.ccannedfood)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cwater)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cgasoline)));
        listacartas.add(new Cartas(getDrawable(R.drawable.cglassbottle)));
    }

    public void Todas(View view) {
        listacartas.clear();
        Crear();
        adaptercartas.notifyDataSetChanged();
        viewCartas.smoothScrollToPosition(0);

    }

    public void Distancia(View view) {
        listacartas.clear();
        DistanciaCartas();
        adaptercartas.notifyDataSetChanged();
        viewCartas.smoothScrollToPosition(0);
    }

    public void Cuerpo(View view) {
        listacartas.clear();
        CuerpoCartas();
        adaptercartas.notifyDataSetChanged();
        viewCartas.smoothScrollToPosition(0);
    }

    public void Especiales(View view) {
        listacartas.clear();
        EspecialesCartas();
        adaptercartas.notifyDataSetChanged();
        viewCartas.smoothScrollToPosition(0);
    }

    public void Otros(View view) {
        listacartas.clear();
        OtrosCartas();
        adaptercartas.notifyDataSetChanged();
        viewCartas.smoothScrollToPosition(0);
    }
}
