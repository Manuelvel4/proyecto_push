package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SelectionActivity extends AppCompatActivity{

    public static String keysala="key_sala";
    public static String keynombre="key_nombre";

    private RecyclerView viewPersonajes;
    private List<Personaje> listaPersonajes;
    private List<String> listaUsuarios;
    private PersonajesAdapter adapterPersonajes;
    private ArrayAdapter<String> adapterUsuarios;
    private LinearLayoutManager linlayoutmanager;
    private ImageView descripcionPersonaje;
    private TextView habAzul,habAmarilla, habNaranja1, habNaranja2, habRoja1, habRoja2,habRoja3;
    private CheckBox modoZombie;
    private int idPersonaje=0;

    private TextView p1Nombre;
    private ImageView p1Foto;
    private TextView p2Nombre;
    private ImageView p2Foto;
    private TextView p3Nombre;
    private ImageView p3Foto;
    private TextView p4Nombre;
    private ImageView p4Foto;
    private TextView p5Nombre;
    private ImageView p5Foto;
    private TextView p6Nombre;
    private ImageView p6Foto;
    private RelativeLayout p1;
    private RelativeLayout p2;
    private RelativeLayout p3;
    private RelativeLayout p4;
    private RelativeLayout p5;
    private RelativeLayout p6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        descripcionPersonaje =(ImageView)findViewById(R.id.DescripcionPersonaje);
        habAzul=(TextView) findViewById(R.id.HabAzul);
        habAmarilla=(TextView)findViewById(R.id.HabAmarilla);
        habNaranja1=(TextView) findViewById(R.id.HabNaranja1);
        habNaranja2=(TextView) findViewById(R.id.HabNaranja2);
        habRoja1=(TextView) findViewById(R.id.HabRoja1);
        habRoja2=(TextView) findViewById(R.id.HabRoja2);
        habRoja3=(TextView) findViewById(R.id.HabRoja3);
        modoZombie=(CheckBox)findViewById(R.id.ModoZombie);

        p1Nombre=(TextView)findViewById(R.id.Personaje1Nombre);
        p1Foto=(ImageView)findViewById(R.id.Personaje1foto);
        p2Nombre=(TextView)findViewById(R.id.Personaje2Nombre);
        p2Foto=(ImageView)findViewById(R.id.Personaje2foto);
        p3Nombre=(TextView)findViewById(R.id.Personaje3Nombre);
        p3Foto=(ImageView)findViewById(R.id.Personaje3foto);
        p4Nombre=(TextView)findViewById(R.id.Personaje4Nombre);
        p4Foto=(ImageView)findViewById(R.id.Personaje4foto);
        p5Nombre=(TextView)findViewById(R.id.Personaje5Nombre);
        p5Foto=(ImageView)findViewById(R.id.Personaje5foto);
        p6Nombre=(TextView)findViewById(R.id.Personaje6Nombre);
        p6Foto=(ImageView)findViewById(R.id.Personaje6foto);

        TextView Sala=(TextView)findViewById(R.id.Sala);
        ListView viewUsuarios=(ListView)findViewById(R.id.ViewUsuarios);
        Sala.setText(getIntent().getExtras().getString(keysala));
        listaUsuarios=new ArrayList<>();
        listaUsuarios.add(getIntent().getExtras().getString(keynombre));
        adapterUsuarios=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaUsuarios);
        viewUsuarios.setAdapter(adapterUsuarios);

        listaPersonajes=new ArrayList<>();
        viewPersonajes =(RecyclerView)findViewById(R.id.ListaPersonajes);
        linlayoutmanager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        viewPersonajes.setLayoutManager(linlayoutmanager);
        CrearPersonajes();
        PersonajeSeleccionado();
        adapterPersonajes =new PersonajesAdapter(this,listaPersonajes);
        viewPersonajes.setAdapter(adapterPersonajes);

        p1=(RelativeLayout)findViewById(R.id.Personaje1);
        p1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                PersonajeEscogido(v,event,1);
                return true;
            }
        });
        p2=(RelativeLayout)findViewById(R.id.Personaje2);
        p2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                PersonajeEscogido(v,event,2);
                return true;
            }
        });
        p3=(RelativeLayout)findViewById(R.id.Personaje3);
        p3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                PersonajeEscogido(v,event,3);
                return true;
            }
        });
        p4=(RelativeLayout)findViewById(R.id.Personaje4);
        p4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                PersonajeEscogido(v,event,4);
                return true;
            }
        });
        p5=(RelativeLayout)findViewById(R.id.Personaje5);
        p5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                PersonajeEscogido(v,event,5);
                return true;
            }
        });
        p6=(RelativeLayout)findViewById(R.id.Personaje6);
        p6.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                PersonajeEscogido(v,event,6);
                return true;
            }
        });

        modoZombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaPersonajes.clear();
                if (modoZombie.isChecked()==false) {
                    CrearPersonajes();
                }else{
                    CrearPersonajesZombies();
                }
                PersonajeSeleccionado();
                adapterPersonajes.notifyDataSetChanged();
             }
        });
        adapterPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                PersonajeSeleccionado();
            }
        });
        adapterPersonajes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                PersonajeSeleccionado();
                if (view.getAlpha()!=(float)0.5){
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    //view.startDragAndDrop(data,shadowBuilder,view,0);
                    view.setAlpha((float)0.5);
                    return true;
                }
                return false;
           }
        });
    }

    private void PersonajeEscogido(View v, DragEvent event, int ps) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                v.setBackgroundColor(getColor(android.R.color.holo_green_light));
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundColor(getColor(android.R.color.holo_green_dark));
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundColor(getColor(android.R.color.holo_green_light));
                break;
            case DragEvent.ACTION_DROP:
                Drop(ps);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundColor(getColor(android.R.color.transparent));
            default:
                break;
        }
    }
    private void Drop(int ps) {
        Personaje p = listaPersonajes.get(idPersonaje);
        if (ps==1){
            p1Nombre.setText(p.getNombre());
            p1Nombre.setBackground(getDrawable(R.drawable.madera));
            p1Foto.setImageDrawable(p.getCara());
        }else if (ps==2){
            p2Nombre.setText(p.getNombre());
            p2Nombre.setBackground(getDrawable(R.drawable.madera));
            p2Foto.setImageDrawable(p.getCara());
        }else if (ps==3) {
            p3Nombre.setText(p.getNombre());
            p3Nombre.setBackground(getDrawable(R.drawable.madera));
            p3Foto.setImageDrawable(p.getCara());
        }else if (ps==4) {
            p4Nombre.setText(p.getNombre());
            p4Nombre.setBackground(getDrawable(R.drawable.madera));
            p4Foto.setImageDrawable(p.getCara());
        }else if (ps==5) {
            p5Nombre.setText(p.getNombre());
            p5Nombre.setBackground(getDrawable(R.drawable.madera));
            p5Foto.setImageDrawable(p.getCara());
        }else if (ps==6) {
            p6Nombre.setText(p.getNombre());
            p6Nombre.setBackground(getDrawable(R.drawable.madera));
            p6Foto.setImageDrawable(p.getCara());
        }
    }
    private void PersonajeSeleccionado() {
        Personaje p = listaPersonajes.get(idPersonaje);
        habAzul.setText(p.getHabAzul());
        habAmarilla.setText(p.getHabAmarilla());
        habNaranja1.setText(p.getHabNaranja1());
        habNaranja2.setText(p.getHabNaranja2());
        habRoja1.setText(p.getHabRoja1());
        habRoja2.setText(p.getHabRoja2());
        habRoja3.setText(p.getHabRoja3());
        descripcionPersonaje.setImageDrawable(p.getFoto());
    }
    private void CrearPersonajes() {
            String nombre="watts";
            String habazul=getString(R.string.EmpezarConBateBeisbol);
            String habamarilla=getString(R.string.mas1accion);
            String habnaranja1=getString(R.string.mas1dadoCuerpoACuerpo);
            String habnaranja2=getString(R.string.Empujon);
            String habroja1=getString(R.string.dosZonasPorAccionDeMovimiento);
            String habroja2=getString( R.string.mas1accionDeCombateGratuita);
            String habroja3=getString(R.string.mas1alasTiradasDeCombate);
            Drawable foto=getDrawable(R.drawable.pwatts);
            Drawable cara=getDrawable(R.drawable.pwattscara);
            listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

            nombre="Joshua";
            habazul=getString(R.string.Socorrista);
            habamarilla=getString(R.string.mas1accion);
            habnaranja1=getString(R.string.mas1acciónDeCombateADistancia);
            habnaranja2=getString(R.string.mas1aLasTiradasCuerpoACuerpo);
            habroja1=getString(R.string.dosZonasPorAccionDeMovimiento);
            habroja2=getString( R.string.mas1accionDeCombateGratuita);
            habroja3=getString(R.string.mas1alasTiradasDeCombate);
            foto=getDrawable(R.drawable.pjoshua);
            cara=getDrawable(R.drawable.pjoshuacara);
            listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

            nombre="Shannon";
            habazul=getString(R.string.DisparoABocajarro);
            habamarilla=getString(R.string.mas1accion);
            habnaranja1=getString(R.string.mas1accionADistanciaGratuita);
            habnaranja2=getString(R.string.Afortunada);
            habroja1=getString(R.string.mas1dadoCombate);
            habroja2=getString( R.string.mas1accionDeCombateGratuita);
            habroja3=getString(R.string.Escurridiza);
            foto=getDrawable(R.drawable.pshannon);
            cara=getDrawable(R.drawable.pshannoncara);
            listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

            nombre="Grindlock";
            habazul=getString(R.string.Provocacion);
            habamarilla=getString(R.string.mas1accion);
            habnaranja1=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
            habnaranja2=getString(R.string.Escurridizo);
            habroja1=getString(R.string.mas1AlDañoCuerpoACuerpo);
            habroja2=getString( R.string.EsoEsTodoLoQueTienes);
            habroja3=getString(R.string.seisEnElDadoMas1DadoDeCombate);
            foto=getDrawable(R.drawable.pgrindlock);
            cara=getDrawable(R.drawable.pgrindlockcara);
            listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

            nombre="Belle";
            habazul=getString(R.string.mas1accionDeMovimientoGratuita);
            habamarilla=getString(R.string.mas1accion);
            habnaranja1=getString(R.string.mas1aLasTiradasADistancia);
            habnaranja2=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
            habroja1=getString(R.string.mas1dadoCombate);
            habroja2=getString( R.string.mas1accionDeMovimientoGratuita);
            habroja3=getString(R.string.Ambidiestra);
            foto=getDrawable(R.drawable.pbelle);
            cara=getDrawable(R.drawable.pbellecara);
            listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

            nombre="Kim";
            habazul=getString(R.string.Afortunada);
            habamarilla=getString(R.string.mas1accion);
            habnaranja1=getString(R.string.seisEnElDadoMas1DadoADistancia);
            habnaranja2=getString(R.string.Amano);
            habroja1=getString(R.string.mas1accionDeCombateGratuita);
            habroja2=getString( R.string.mas1alasTiradasDeCombate);
            habroja3=getString(R.string.seisEnElDadoMas1DadoCuerpoACuerpo);
            foto=getDrawable(R.drawable.pkim);
            cara=getDrawable(R.drawable.pkimcara);
            listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));
    }
    private void CrearPersonajesZombies() {
        String nombre="watts";
        String habazul=getString(R.string.EmpezarConBateBeisbol);
        String habamarilla=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        String habnaranja1=getString(R.string.FrenesiCuerpoACuerpo);
        String habnaranja2=getString(R.string.Empujon);
        String habroja1=getString(R.string.dosZonasPorAccionDeMovimiento);
        String habroja2=getString( R.string.mas1alasTiradasDeCombate);
        String habroja3=getString(R.string.FrenesiCombate);
        Drawable foto=getDrawable(R.drawable.pwattszombie);
        Drawable cara=getDrawable(R.drawable.pwattscarazombie);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Joshua";
        habazul=getString(R.string.Socorrista);
        habamarilla=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1=getString(R.string.mas1aLasTiradasCuerpoACuerpo);
        habnaranja2=getString(R.string.SuperFuerza);
        habroja1=getString(R.string.mas1aLasTiradasADistancia);
        habroja2=getString( R.string.LiderNato);
        habroja3=getString(R.string.Regeneracion);
        foto=getDrawable(R.drawable.pjoshuazombie);
        cara=getDrawable(R.drawable.pjoshuacarazombie);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Shannon";
        habazul=getString(R.string.DisparoABocajarro);
        habamarilla=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1=getString(R.string.FrenesiAdistancia);
        habnaranja2=getString(R.string.Afortunada);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.Escurridiza);
        habroja3=getString(R.string.SegadoraCombate);
        foto=getDrawable(R.drawable.pshannonzombie);
        cara=getDrawable(R.drawable.pshannoncarazombie);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Grindlock";
        habazul=getString(R.string.Provocacion);
        habamarilla=getString(R.string.mas1accionDeCombateCuerpoACuerpoGratuita);
        habnaranja1=getString(R.string.VinculoZombi);
        habnaranja2=getString(R.string.Escurridizo);
        habroja1=getString(R.string.mas1AlDañoCuerpoACuerpo);
        habroja2=getString( R.string.SegadoraCombate);
        habroja3=getString(R.string.seisEnElDadoMas1DadoDeCombate);
        foto=getDrawable(R.drawable.pgrindlockzombie);
        cara=getDrawable(R.drawable.pgrindlockcarazombie);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Belle";
        habazul=getString(R.string.mas1accionDeMovimientoGratuita);
        habamarilla=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1=getString(R.string.mas1dadoADistancia);
        habnaranja2=getString(R.string.VinculoZombi);
        habroja1=getString(R.string.mas1dadoCombate);
        habroja2=getString( R.string.Regeneracion);
        habroja3=getString(R.string.Ambidiestra);
        foto=getDrawable(R.drawable.pbellezombie);
        cara=getDrawable(R.drawable.pbellecarazombie);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

        nombre="Kim";
        habazul=getString(R.string.Afortunada);
        habamarilla=getString(R.string.mas1accionADistanciaGratuita);
        habnaranja1=getString(R.string.SegadoraCombate);
        habnaranja2=getString(R.string.Amano);
        habroja1=getString(R.string.mas1alasTiradasDeCombate);
        habroja2=getString( R.string.seisEnElDadoMas1DadoCuerpoACuerpo);
        habroja3=getString(R.string.VinculoZombi);
        foto=getDrawable(R.drawable.pkimzombie);
        cara=getDrawable(R.drawable.pkimcarazombie);
        listaPersonajes.add(new Personaje(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

    }
    public void Atras(View view) {
        Intent intent=new Intent(this,CrearActivity.class);
        startActivity(intent);
        finish();
    }
}

