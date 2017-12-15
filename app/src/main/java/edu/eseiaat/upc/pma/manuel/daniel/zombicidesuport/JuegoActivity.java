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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity {


    public static String KeyListaPersonajes="key_listaPersonajes";
    private int[] union;
    private TextView habAzul,habAmarilla, habNaranja1, habNaranja2, habRoja1, habRoja2,habRoja3,nombre;
    private ImageView foto;
    private ArrayList<Personajes> listaPersonajes;
    private ArrayList<Personajes> listaPersonajeszombie;
    private ArrayList<Personajes> listaPersonajesSelec;
    private RecyclerView viewPersonajes;
    private LinearLayoutManager linlayoutmanager;
    private PersonajesAdapter adapterPersonajes;
    private int idPersonaje;
    private ImageView carta1,carta2,carta3,carta4,carta5;
    private boolean[] drop;
    private Switch modozombie;

    // BARRA_TABLA_PUNTOS
    ArrayList<BARRA> lista;
    ArrayList<Integer> lista_Draw;
    ArrayList<Integer> lista_red;
    RecyclerView recy;
    Button btn_plus, btn_less;
    int CONTADOR_VUELTA=0;
    int numero =1;
    int numero_less =0;
    boolean primera_vuelta=false;
    boolean end= false;
    boolean btn_mas_pulsado=false;
    boolean btn_menos_pulsado =false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        habAzul=(TextView) findViewById(R.id.HabAzul);
        habAmarilla=(TextView)findViewById(R.id.HabAmarilla);
        habNaranja1=(TextView) findViewById(R.id.HabNaranja1);
        habNaranja2=(TextView) findViewById(R.id.HabNaranja2);
        habRoja1=(TextView) findViewById(R.id.HabRoja1);
        habRoja2=(TextView) findViewById(R.id.HabRoja2);
        habRoja3=(TextView) findViewById(R.id.HabRoja3);
        carta1=(ImageView)findViewById(R.id.Carta1);
        carta2=(ImageView)findViewById(R.id.Carta2);
        carta3=(ImageView)findViewById(R.id.Carta3);
        carta4=(ImageView)findViewById(R.id.Carta4);
        carta5=(ImageView)findViewById(R.id.Carta5);
        foto=(ImageView)findViewById(R.id.foto);
        nombre=(TextView)findViewById(R.id.nombre);
        modozombie = (Switch) findViewById(R.id.ModoZombie);

        union=getIntent().getIntArrayExtra(KeyListaPersonajes);
        listaPersonajes=new ArrayList<>();
        listaPersonajeszombie=new ArrayList<>();
        listaPersonajesSelec=new ArrayList<>();

        CrearPersonajes();
        CrearPersonajesZombies();
        ListaPersonajesSelec();
        TablaPuntos();

        viewPersonajes =(RecyclerView)findViewById(R.id.ViewPersonajes);
        linlayoutmanager =new LinearLayoutManager(this);
        viewPersonajes.setLayoutManager(linlayoutmanager);
        adapterPersonajes =new PersonajesAdapter(this,listaPersonajesSelec);
        viewPersonajes.setAdapter(adapterPersonajes);
        idPersonaje=0;

        drop=new boolean[5];

        inicio();
        PersonajeSelec();

        adapterPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idPersonaje=viewPersonajes.getChildAdapterPosition(view);
                PersonajeSelec();
            }
        });
        carta1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[0]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personajes p=listaPersonajesSelec.get(idPersonaje);
                        if (drop[1]){
                            p.carta1=carta2.getDrawable();
                            p.carta2=carta1.getDrawable();
                        }
                        if (drop[2]){
                            p.carta1=carta3.getDrawable();
                            p.carta3=carta1.getDrawable();
                        }
                        if (drop[3]){
                            p.carta1=carta4.getDrawable();
                            p.carta4=carta1.getDrawable();
                        }
                        if (drop[4]){
                            p.carta1=carta5.getDrawable();
                            p.carta5=carta1.getDrawable();
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[1]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personajes p=listaPersonajesSelec.get(idPersonaje);
                        if (drop[0]){
                            p.carta2=carta1.getDrawable();
                            p.carta1=carta2.getDrawable();
                        }
                        if (drop[2]){
                            p.carta2=carta3.getDrawable();
                            p.carta3=carta2.getDrawable();
                        }
                        if (drop[3]){
                            p.carta2=carta4.getDrawable();
                            p.carta4=carta2.getDrawable();
                        }
                        if (drop[4]){
                            p.carta2=carta5.getDrawable();
                            p.carta5=carta2.getDrawable();
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[2]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personajes p=listaPersonajesSelec.get(idPersonaje);
                        if (drop[0]){
                            p.carta3=carta1.getDrawable();
                            p.carta1=carta3.getDrawable();
                        }
                        if (drop[1]){
                            p.carta3=carta2.getDrawable();
                            p.carta2=carta3.getDrawable();
                        }
                        if (drop[3]){
                            p.carta3=carta4.getDrawable();
                            p.carta4=carta3.getDrawable();
                        }
                        if (drop[4]){
                            p.carta3=carta5.getDrawable();
                            p.carta5=carta3.getDrawable();
                        }
                        PersonajeSelec();
                        ResetDrop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[3]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personajes p=listaPersonajesSelec.get(idPersonaje);
                        if (drop[0]){
                            p.carta4=carta1.getDrawable();
                            p.carta1=carta4.getDrawable();
                        }
                        if (drop[1]){
                            p.carta4=carta2.getDrawable();
                            p.carta2=carta4.getDrawable();
                        }
                        if (drop[2]){
                            p.carta4=carta3.getDrawable();
                            p.carta3=carta4.getDrawable();
                        }
                        if (drop[4]){
                            p.carta4=carta5.getDrawable();
                            p.carta5=carta4.getDrawable();
                        }
                        PersonajeSelec();
                        ResetDrop();

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        carta5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                drop[4]=true;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.startDragAndDrop(data,shadowBuilder,view,0);
                return true;

            }
        });
        carta5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Personajes p=listaPersonajesSelec.get(idPersonaje);
                        if (drop[0]){
                            p.carta5=carta1.getDrawable();
                            p.carta1=carta5.getDrawable();
                        }
                        if (drop[1]){
                            p.carta5=carta2.getDrawable();
                            p.carta2=carta5.getDrawable();
                        }
                        if (drop[2]){
                            p.carta5=carta3.getDrawable();
                            p.carta3=carta5.getDrawable();
                        }
                        if (drop[3]){
                            p.carta5=carta4.getDrawable();
                            p.carta4=carta5.getDrawable();
                        }
                        PersonajeSelec();
                        ResetDrop();

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        modozombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personajes p=listaPersonajesSelec.get(idPersonaje);
                for (int i=0;i<listaPersonajes.size();i++){
                    Personajes q=listaPersonajes.get(i);
                    if (p.getNombre().equals(q.getNombre())){
                        if (modozombie.isChecked()){
                            listaPersonajesSelec.add(listaPersonajeszombie.get(i));
                            Personajes r=listaPersonajesSelec.get(listaPersonajesSelec.size()-1);
                            r.setModozombie(true);
                            CambioModo();
                        }else{
                            listaPersonajesSelec.add(listaPersonajes.get(i));
                            Personajes r=listaPersonajesSelec.get(listaPersonajesSelec.size()-1);
                            r.setModozombie(false);
                            CambioModo();
                        }
                    }
                }
                listaPersonajesSelec.remove(idPersonaje);
                idPersonaje=listaPersonajesSelec.size()-1;
                adapterPersonajes.notifyDataSetChanged();
                PersonajeSelec();

            }
        });
        carta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(JuegoActivity.this,CardsActivity.class);
                Personajes p=listaPersonajesSelec.get(idPersonaje);
                //int a =carta1.getTag();
                //intent.putExtra(CardsActivity.Keycarta1,a);
                startActivity(intent);
            }
        });


    }

    private void CambioModo() {
        Personajes p=listaPersonajesSelec.get(idPersonaje);
        Personajes r=listaPersonajesSelec.get(listaPersonajesSelec.size()-1);
        r.carta1=p.carta1;
        r.carta2=p.carta2;
        r.carta3=p.carta3;
        r.carta4=p.carta4;
        r.carta5=p.carta5;
        r.level=p.level;
    }

    private void ResetDrop() {
        for (int i=0;i<drop.length;i++){
            drop[i]=false;
        }
    }
    private void inicio() {
        for (int i=0; i<listaPersonajesSelec.size();i++){
            Personajes p=listaPersonajesSelec.get(i);
            p.carta1=getDrawable(R.drawable.cartamano);
            p.carta2=getDrawable(R.drawable.cartamano);
            p.carta3=getDrawable(R.drawable.cartamano);
            p.carta4=getDrawable(R.drawable.cartamano);
            p.carta5=getDrawable(R.drawable.cartamano);

            if (p.getNombre()=="watts"){
                p.carta1=getDrawable(R.drawable.cbaseballbat);
            }

        }
    }
    private void ListaPersonajesSelec() {
        boolean[] level=new boolean[6];
        for (int i=0;i<level.length;i++){
            level[i]=false;
        }
        for (int i=0;i<union.length;i++){
            Personajes p=listaPersonajes.get(union[i]);
            listaPersonajesSelec.add(p);
            Personajes ps=listaPersonajesSelec.get(i);
            ps.setLevel(level);
        }
    }
    private void PersonajeSelec() {
        Personajes p = listaPersonajesSelec.get(idPersonaje);
        habAzul.setText(p.getHabAzul());
        habAmarilla.setText(p.getHabAmarilla());
        habNaranja1.setText(p.getHabNaranja1());
        habNaranja2.setText(p.getHabNaranja2());
        habRoja1.setText(p.getHabRoja1());
        habRoja2.setText(p.getHabRoja2());
        habRoja3.setText(p.getHabRoja3());
        foto.setImageDrawable(p.getCara());
        nombre.setText(p.getNombre());
        carta1.setImageDrawable(p.getCarta1());
        carta2.setImageDrawable(p.getCarta2());
        carta3.setImageDrawable(p.getCarta3());
        carta4.setImageDrawable(p.getCarta4());
        carta5.setImageDrawable(p.getCarta5());
        if (p.isModozombie()){
            modozombie.setChecked(true);
        }else{
            modozombie.setChecked(false);
        }
        if (!p.level[0]){
            habAmarilla.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[1]){
            habNaranja1.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[2]){
            habNaranja2.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[3]){
            habRoja1.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[4]){
            habRoja2.setBackgroundColor(getColor(android.R.color.white));
        }
        if (!p.level[5]){
            habRoja3.setBackgroundColor(getColor(android.R.color.white));
        }
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
        listaPersonajes.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajes.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajes.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajes.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajes.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajes.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));
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
        listaPersonajeszombie.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajeszombie.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajeszombie.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajeszombie.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajeszombie.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

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
        listaPersonajeszombie.add(new Personajes(nombre,habazul,habamarilla,habnaranja1,habnaranja2,habroja1,habroja2,habroja3,foto,cara));

    }

    //TABLA DE PUNTOS
    private void TablaPuntos() {
        // creo una lista de Barra y tambien una lista unicamente de niveles para poder modificarlo

        lista =new ArrayList<>();
        lista_Draw = new ArrayList<>();
        lista_red =new ArrayList<>();


        recy = (RecyclerView) findViewById(R.id.ViewLevel);
        btn_plus = (Button)findViewById(R.id.btn_more);
        btn_less = (Button)findViewById(R.id.btn_less);

        recy.setLayoutManager( new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        llenar_DATOS();

        final AdaptadorBarra adaptarBarra = new AdaptadorBarra(lista);

        recy.setAdapter(adaptarBarra);


        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Move_plus(adaptarBarra);
                if (end) btn_plus.setEnabled(false);
            }
        });


        btn_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numero_less>0) move_less(adaptarBarra);



            }
        });
    }

    private void move_less(AdaptadorBarra adaptarBarra) {

        if(btn_mas_pulsado&&!primera_vuelta) numero_less =numero-1;
        if (primera_vuelta){numero_less = 43; primera_vuelta=false; CONTADOR_VUELTA --;}
        if(end) {numero_less=43;end =false;}

        if(numero_less==43){
            lista.set(43,new BARRA(lista_red.get(43)));
            lista.set(42,new BARRA(R.drawable.puntero,lista_red.get(42)));
            btn_plus.setEnabled(true);
            primera_vuelta = false;
            Toast.makeText(getApplicationContext(), "false:" +primera_vuelta, Toast.LENGTH_LONG).show();

            numero_less = 42;
            numero =43;
        }

        else if (numero_less>0){
            lista.set(numero_less,new BARRA(lista_red.get(numero_less)));
            lista.set(numero_less-1,new BARRA(R.drawable.puntero,lista_red.get(numero_less-1)));
            btn_plus.setEnabled(true);
            numero_less--;

        }

        btn_menos_pulsado = true;
        btn_mas_pulsado =false;
        adaptarBarra.notifyDataSetChanged();
    }

    private void Move_plus(AdaptadorBarra adaptarBarra) {


        if (!primera_vuelta) {
            if(btn_menos_pulsado) numero =numero_less +1;
            lista.set(numero - 1, new BARRA(lista_red.get(numero - 1)));
            lista.set(numero, new BARRA(R.drawable.puntero, lista_red.get(numero)));

            if (numero < 43){ numero++;numero_less++;}

            else {
                if (CONTADOR_VUELTA < 1) {
                    numero = 1;
                    CONTADOR_VUELTA++;
                    primera_vuelta = true;
                    Toast.makeText(getApplicationContext(), "VueltaNumero:" + CONTADOR_VUELTA+primera_vuelta, Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(getApplicationContext(), "end:" + end, Toast.LENGTH_LONG).show();

                    end = true;}
            }


        }

        else if (primera_vuelta) {
            lista.set(43, new BARRA(lista_red.get(43)));
            lista.set(0, new BARRA(R.drawable.puntero, lista_red.get(0)));

            primera_vuelta = false;
            numero_less =0;

        }

        btn_mas_pulsado =true;

        btn_menos_pulsado = false;

        adaptarBarra.notifyDataSetChanged();
    }


    private void llenar_DATOS(){

        lista_Draw.add(R.drawable.level_43);
        lista_Draw.add(R.drawable.level_42);
        lista_Draw.add(R.drawable.level_41);
        lista_Draw.add(R.drawable.level_40);
        lista_Draw.add(R.drawable.level_39);
        lista_Draw.add(R.drawable.level_38);
        lista_Draw.add(R.drawable.level_37);
        lista_Draw.add(R.drawable.level_36);
        lista_Draw.add(R.drawable.level_35);
        lista_Draw.add(R.drawable.level_34);
        lista_Draw.add(R.drawable.level_33);
        lista_Draw.add(R.drawable.level_32);
        lista_Draw.add(R.drawable.level_31);
        lista_Draw.add(R.drawable.level_30);
        lista_Draw.add(R.drawable.level_29);
        lista_Draw.add(R.drawable.level_28);
        lista_Draw.add(R.drawable.level_27);
        lista_Draw.add(R.drawable.level_26);
        lista_Draw.add(R.drawable.level_25);
        lista_Draw.add(R.drawable.level_24);
        lista_Draw.add(R.drawable.level_23);
        lista_Draw.add(R.drawable.level_22);
        lista_Draw.add(R.drawable.level_21);
        lista_Draw.add(R.drawable.level_20);
        lista_Draw.add(R.drawable.level_19);
        lista_Draw.add(R.drawable.level_18);
        lista_Draw.add(R.drawable.level_17);
        lista_Draw.add(R.drawable.level_16);
        lista_Draw.add(R.drawable.level_15);
        lista_Draw.add(R.drawable.level_14);
        lista_Draw.add(R.drawable.level_13);
        lista_Draw.add(R.drawable.level_12);
        lista_Draw.add(R.drawable.level_11);
        lista_Draw.add(R.drawable.level_10);
        lista_Draw.add(R.drawable.level_9);
        lista_Draw.add(R.drawable.level_8);
        lista_Draw.add(R.drawable.level_7);
        lista_Draw.add(R.drawable.level_6);
        lista_Draw.add(R.drawable.level_5);
        lista_Draw.add(R.drawable.level_4);
        lista_Draw.add(R.drawable.level_3);
        lista_Draw.add(R.drawable.level_2);
        lista_Draw.add(R.drawable.level_1);
        lista_Draw.add(R.drawable.level_0);

        lista_red.add(R.drawable.red_0);
        lista_red.add(R.drawable.red_1);
        lista_red.add(R.drawable.red_2);
        lista_red.add(R.drawable.red_3);
        lista_red.add(R.drawable.red_4);
        lista_red.add(R.drawable.red_5);
        lista_red.add(R.drawable.red_6);
        lista_red.add(R.drawable.red_7);
        lista_red.add(R.drawable.red_8);
        lista_red.add(R.drawable.red_9);
        lista_red.add(R.drawable.red_10);
        lista_red.add(R.drawable.red_11);
        lista_red.add(R.drawable.red_12);
        lista_red.add(R.drawable.red_13);
        lista_red.add(R.drawable.red_14);
        lista_red.add(R.drawable.red_15);
        lista_red.add(R.drawable.red_16);
        lista_red.add(R.drawable.red_17);
        lista_red.add(R.drawable.red_18);
        lista_red.add(R.drawable.red_19);
        lista_red.add(R.drawable.red_20);
        lista_red.add(R.drawable.red_21);
        lista_red.add(R.drawable.red_22);
        lista_red.add(R.drawable.red_23);
        lista_red.add(R.drawable.red_24);
        lista_red.add(R.drawable.red_25);
        lista_red.add(R.drawable.red_26);
        lista_red.add(R.drawable.red_27);
        lista_red.add(R.drawable.red_28);
        lista_red.add(R.drawable.red_29);
        lista_red.add(R.drawable.red_30);
        lista_red.add(R.drawable.red_31);
        lista_red.add(R.drawable.red_32);
        lista_red.add(R.drawable.red_33);
        lista_red.add(R.drawable.red_34);
        lista_red.add(R.drawable.red_35);
        lista_red.add(R.drawable.red_36);
        lista_red.add(R.drawable.red_37);
        lista_red.add(R.drawable.red_38);
        lista_red.add(R.drawable.red_39);
        lista_red.add(R.drawable.red_40);
        lista_red.add(R.drawable.red_41);
        lista_red.add(R.drawable.red_42);
        lista_red.add(R.drawable.red_43);
        lista.add(new BARRA(R.drawable.puntero,R.drawable.red_0));
        lista.add(new BARRA(R.drawable.level_1));
        lista.add(new BARRA(R.drawable.level_2));
        lista.add(new BARRA(R.drawable.level_3));
        lista.add(new BARRA(R.drawable.level_4));
        lista.add(new BARRA(R.drawable.level_5));
        lista.add(new BARRA(R.drawable.level_6));
        lista.add(new BARRA(R.drawable.level_7));
        lista.add(new BARRA(R.drawable.level_8));
        lista.add(new BARRA(R.drawable.level_9));
        lista.add(new BARRA(R.drawable.level_10));
        lista.add(new BARRA(R.drawable.level_11));
        lista.add(new BARRA(R.drawable.level_12));
        lista.add(new BARRA(R.drawable.level_13));
        lista.add(new BARRA(R.drawable.level_14));
        lista.add(new BARRA(R.drawable.level_15));
        lista.add(new BARRA(R.drawable.level_16));
        lista.add(new BARRA(R.drawable.level_17));
        lista.add(new BARRA(R.drawable.level_18));
        lista.add(new BARRA(R.drawable.level_19));
        lista.add(new BARRA(R.drawable.level_20));
        lista.add(new BARRA(R.drawable.level_21));
        lista.add(new BARRA(R.drawable.level_22));
        lista.add(new BARRA(R.drawable.level_23));
        lista.add(new BARRA(R.drawable.level_24));
        lista.add(new BARRA(R.drawable.level_25));
        lista.add(new BARRA(R.drawable.level_26));
        lista.add(new BARRA(R.drawable.level_27));
        lista.add(new BARRA(R.drawable.level_28));
        lista.add(new BARRA(R.drawable.level_29));
        lista.add(new BARRA(R.drawable.level_30));
        lista.add(new BARRA(R.drawable.level_31));
        lista.add(new BARRA(R.drawable.level_32));
        lista.add(new BARRA(R.drawable.level_33));
        lista.add(new BARRA(R.drawable.level_34));
        lista.add(new BARRA(R.drawable.level_35));
        lista.add(new BARRA(R.drawable.level_36));
        lista.add(new BARRA(R.drawable.level_37));
        lista.add(new BARRA(R.drawable.level_38));
        lista.add(new BARRA(R.drawable.level_39));
        lista.add(new BARRA(R.drawable.level_40));
        lista.add(new BARRA(R.drawable.level_41));
        lista.add(new BARRA(R.drawable.level_42));
        lista.add(new BARRA(R.drawable.level_43));

    }


}
