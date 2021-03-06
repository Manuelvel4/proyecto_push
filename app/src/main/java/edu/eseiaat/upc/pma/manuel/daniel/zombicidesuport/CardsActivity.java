package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class CardsActivity extends AppCompatActivity {
    public static String Keycarta1="key_carta1";

    private ArrayList<Cartas> listacartas;
    private CartasAdapter adaptercartas;
    private RecyclerView viewCartas;
    private LinearLayoutManager linlayoutmanager;
    private ImageView carta1,carta2,carta3,carta4,carta5;
    private Drawable cartaselect;
    private int idcarta;


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

        carta1=(ImageView)findViewById(R.id.Carta1);
        carta2=(ImageView)findViewById(R.id.Carta2);
        carta3=(ImageView)findViewById(R.id.Carta3);
        carta4=(ImageView)findViewById(R.id.Carta4);
        carta5=(ImageView)findViewById(R.id.Carta5);

        adaptercartas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                idcarta=viewCartas.getChildAdapterPosition(view);
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;
            }
        });
        carta1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Seleccionar();
                        carta1.setImageDrawable(cartaselect);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Seleccionar();
                        carta2.setImageDrawable(cartaselect);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Seleccionar();
                        carta3.setImageDrawable(cartaselect);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Seleccionar();
                        carta4.setImageDrawable(cartaselect);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Seleccionar();
                        carta5.setImageDrawable(cartaselect);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        /*Intent intent = getIntent();
        int a = intent.getExtras().getInt(Keycarta1);
        ImageView icono = (ImageView) findViewById(R.id.Carta1);
        icono.setImageDrawable(getDrawable(R.drawable.calavera));*/
    }

    private void Seleccionar() {
        Cartas c=listacartas.get(idcarta);
        cartaselect=c.getCarta();
    }

    private void Crear() {
        OtrosCartas();
        EspecialesCartas();
        CuerpoCartas();
        DistanciaCartas();
    }

    private void DistanciaCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cmashotgun),"cmashotgun"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ceviltwins),"ceviltwins"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cpistol),"cpistol"));
        listacartas.add(new Cartas(getDrawable(R.drawable.crifle),"crifle"));
        listacartas.add(new Cartas(getDrawable(R.drawable.csawedoff),"csawedoff"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cshotgun),"cshotgun"));
        listacartas.add(new Cartas(getDrawable(R.drawable.csubmg),"csubmg"));
    }

    private void CuerpoCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cbaseballbat),"cbaseballbat"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cchainsaw),"cchainsaw"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ccrowbar),"ccrowbar"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cfireaxe),"cfireaxe"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ckatana),"ckatana"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmachete),"cmachete"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cpan),"cpan"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmashotgun),"cmashotgun"));
    }

    private void EspecialesCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cgoaliemask),"cgoaliemask"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cflashlight),"cflashlight"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cplentyofammo),"cplentyofammo"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cplentyofammoshotgun),"cplentyofammoshotgun"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cscope),"cscope"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cmolotov),"cmolotov"));
    }

    private void OtrosCartas() {
        listacartas.add(new Cartas(getDrawable(R.drawable.cbagofrice),"cbagofrice"));
        listacartas.add(new Cartas(getDrawable(R.drawable.ccannedfood),"ccannedfood"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cwater),"cwater"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cgasoline),"cgasoline"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cglassbottle),"cglassbottle"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cwound),"cwound"));
        listacartas.add(new Cartas(getDrawable(R.drawable.cartamano),"cartamano"));
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
