package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by PortatilDani on 17/11/2017.
 */

public class Personaje implements Parcelable{
    public String nombre,habAzul, habAmarilla, habNaranja1,habNaranja2, habRoja1,HabRoja2,habRoja3;
    public Drawable foto,cara;
    public boolean invisible;

    public Personaje(String nombre, String habAzul, String habAmarilla, String habNaranja1, String habNaranja2, String habRoja1, String habRoja2, String habRoja3, Drawable foto, Drawable cara) {
        this.nombre = nombre;
        this.habAzul = habAzul;
        this.habAmarilla = habAmarilla;
        this.habNaranja1 = habNaranja1;
        this.habNaranja2 = habNaranja2;
        this.habRoja1 = habRoja1;
        HabRoja2 = habRoja2;
        this.habRoja3 = habRoja3;
        this.foto = foto;
        this.cara = cara;
    }

    public Personaje(String nombre, Drawable cara, boolean invisible) {
        this.nombre = nombre;
        this.cara = cara;
        this.invisible = invisible;
    }

    protected Personaje(Parcel in) {
        nombre = in.readString();
        habAzul = in.readString();
        habAmarilla = in.readString();
        habNaranja1 = in.readString();
        habNaranja2 = in.readString();
        habRoja1 = in.readString();
        HabRoja2 = in.readString();
        habRoja3 = in.readString();
        invisible = in.readByte() != 0;
    }

    public static final Creator<Personaje> CREATOR = new Creator<Personaje>() {
        @Override
        public Personaje createFromParcel(Parcel in) {
            return new Personaje(in);
        }

        @Override
        public Personaje[] newArray(int size) {
            return new Personaje[size];
        }
    };

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabAzul() {
        return habAzul;
    }

    public String getHabAmarilla() {
        return habAmarilla;
    }

    public String getHabNaranja1() {
        return habNaranja1;
    }

    public String getHabNaranja2() {
        return habNaranja2;
    }

    public String getHabRoja1() {
        return habRoja1;
    }

    public String getHabRoja2() {
        return HabRoja2;
    }

    public String getHabRoja3() {
        return habRoja3;
    }

    public Drawable getFoto() {
        return foto;
    }

    public Drawable getCara() {
        return cara;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(habAzul);
        parcel.writeString(habAmarilla);
        parcel.writeString(habNaranja1);
        parcel.writeString(habNaranja2);
        parcel.writeString(habRoja1);
        parcel.writeString(HabRoja2);
        parcel.writeString(habRoja3);
        parcel.writeByte((byte) (invisible ? 1 : 0));
    }
}

