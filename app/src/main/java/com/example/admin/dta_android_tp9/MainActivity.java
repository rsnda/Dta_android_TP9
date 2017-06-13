package com.example.admin.dta_android_tp9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends Traceur {

    Point compteur = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bouton_send = (Button) findViewById(R.id.button_main);
        bouton_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }

        });


        final EditText et_nombre = (EditText) findViewById(R.id.editText);
        Log.d("MAIN", "compteur = " + compteur);
        et_nombre.setText(String.valueOf(compteur.getCompteur()));

        Button bouton_increment = (Button) findViewById(R.id.button_increment);
        bouton_increment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("MAIN", "ON CLICK compteur = " + compteur);
                compteur.setCompteur(compteur.getCompteur() + 1);// Integer.parseInt(et_nombre.getText().toString()) + 1;
                et_nombre.setText(String.valueOf(compteur.getCompteur()));
            }
        });

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        Point valeur_compteur = savedInstanceState.getParcelable("increment_valor");
        final EditText et_nombre = (EditText) findViewById(R.id.editText);
        Log.d("STATE", "Valeur compteur = " + valeur_compteur);
        compteur = valeur_compteur;
        et_nombre.setText(String.valueOf(valeur_compteur.getCompteur()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        Log.d("STATE", "Valeur compteur = " + compteur);
        outState.putParcelable("increment_valor", compteur);
    }

}
