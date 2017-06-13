package com.example.admin.dta_android_tp9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

public class MainActivity extends Traceur {

    Point compteur = new Point();

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chargerParametres();

        Button bouton_send = (Button) findViewById(R.id.button_main);
        bouton_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }

        });


        final EditText et_nombre = (EditText) findViewById(R.id.editText);
        Log.d("MAIN", "compteur = " + compteur.getCompteur());
        et_nombre.setText(String.valueOf(compteur.getCompteur()));

        Button bouton_increment = (Button) findViewById(R.id.button_increment);
        bouton_increment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("MAIN", "ON CLICK compteur = " + compteur);
                compteur.setCompteur(compteur.getCompteur() + 1);
                et_nombre.setText(String.valueOf(compteur.getCompteur()));
            }
        });

    }

    private void chargerParametres() {
        sharedPreferences = getSharedPreferences("TP9", MODE_PRIVATE);
        if(sharedPreferences.contains("PREFS")){
            int progress = sharedPreferences.getInt("PREFS", 0);
            compteur.setCompteur(progress);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        sauvegarderParametres();
    }

    private void sauvegarderParametres(){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("PREFS", compteur.getCompteur());
        edit.commit();
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
