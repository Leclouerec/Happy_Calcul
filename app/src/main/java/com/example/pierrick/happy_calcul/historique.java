package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class historique extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        TextView utilisateur = (TextView) findViewById(R.id.textView_historique);
        utilisateur.setText(listeUtilisateurs.current.getName().toUpperCase());
    }

    public void delete(View view) {
        Log.e("youpi","j'ai reussi ma vie" + listeUtilisateurs.current.getName());
    }

    public void previous(View view){
        Context context = this;

        button b = new button();
        b.previous(context);

    }
}
