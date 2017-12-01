package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

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
    private TableLayout tableLayout;
    private ImageView descripcionPersonaje;
    private TextView habAzul,habAmarilla, habNaranja1, habNaranja2, habRoja1, habRoja2,habRoja3;
    private CheckBox modoZombie;
    private int idPersonaje=0;
    private ArrayList<Personaje> listaPersonajesSelec;
    private RecyclerView viewPersonajesSelec;
    private PersonajesAdapter adapterPersonajesSelec;
    private ImageView borrar;

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
        borrar=(ImageView)findViewById(R.id.Borrar);


        TextView Sala=(TextView)findViewById(R.id.Sala);
        ListView viewUsuarios=(ListView)findViewById(R.id.ViewUsuarios);
        Sala.setText(getIntent().getExtras().getString(keysala));
        listaUsuarios=new ArrayList<>();
        listaUsuarios.add(getIntent().getExtras().getString(keynombre));
        adapterUsuarios=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaUsuarios);
        viewUsuarios.setAdapter(adapterUsuarios);

        listaPersonajes=new ArrayList<>();

        viewPersonajes =(RecyclerView)findViewById(R.id.ListaPersonajes);
        linlayoutmanager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewPersonajes.setLayoutManager(linlayoutmanager);
        CrearPersonajes();
        PersonajeSeleccionado();
        adapterPersonajes =new PersonajesAdapter(this,listaPersonajes);
        viewPersonajes.setAdapter(adapterPersonajes);

        listaPersonajesSelec =new ArrayList<>();
        viewPersonajesSelec =(RecyclerView)findViewById(R.id.ListaSeleccionados);
        linlayoutmanager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewPersonajesSelec.setLayoutManager(linlayoutmanager);
        adapterPersonajesSelec =new PersonajesAdapter(this,listaPersonajesSelec);
        viewPersonajesSelec.setAdapter(adapterPersonajesSelec);

        borrar.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        v.setBackgroundColor(getColor(android.R.color.holo_red_light));
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(getColor(android.R.color.holo_red_dark));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(getColor(android.R.color.holo_red_light));
                        break;
                    case DragEvent.ACTION_DROP:
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setBackgroundColor(getColor(android.R.color.transparent));
                    default:
                        break;
                }
                return true;
            }
        });
        viewPersonajesSelec.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                PersonajeEscogido(view,dragEvent);
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

    private void PersonajeEscogido(View v, DragEvent event) {
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
                Personaje p=listaPersonajes.get(idPersonaje);
                Drawable cara=p.getCara();
                String nombre=p.getNombre();
                listaPersonajesSelec.add(new Personaje (nombre,cara));
                adapterPersonajesSelec.notifyDataSetChanged();
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundColor(getColor(android.R.color.transparent));
            default:
                break;
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

