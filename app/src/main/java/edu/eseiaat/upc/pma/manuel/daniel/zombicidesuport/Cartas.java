package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.graphics.drawable.Drawable;

/**
 * Created by PortatilDani on 11/12/2017.
 */

public class Cartas {
    public Drawable carta;
    public String nombre;

    public Cartas(Drawable carta, String nombre) {
        this.carta = carta;
        this.nombre = nombre;
    }

    public Drawable getCarta() {
        return carta;
    }

    public void setCarta(Drawable carta) {
        this.carta = carta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
