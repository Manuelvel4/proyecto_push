package edu.eseiaat.upc.pma.manuel.daniel.zombicidesuport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CardsActivity extends AppCompatActivity {
    public static String Keycarta1="key_carta1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        /*Intent intent = getIntent();
        Bitmap bitmap = intent.getParcelableExtra(Keycarta1);
        ImageView icono = (ImageView) findViewById(R.id.Carta1);
        icono.setImageBitmap(bitmap);*/
    }
}
