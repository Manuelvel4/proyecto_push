package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.graphics.drawable.Drawable;

/**
 * Created by PortatilDani on 11/12/2017.
 */

public class Cartas {
    public Drawable carta;

    public Cartas(Drawable carta) {
        this.carta = carta;
    }

    public void setCarta(Drawable carta) {
        this.carta = carta;
    }

    public Drawable getCarta() {
        return carta;
    }
}
