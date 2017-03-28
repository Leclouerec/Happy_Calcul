package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class apprentissage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprentissage);

    }



    public void previous(View view){
        Context context = this;

        button b = new button();
        b.previous(context);

    }

    public void home(View view){

        button b = new button();
        b.home(view, this);
    }

    public void jeux(View view){
        Intent intent = new Intent(apprentissage.this, page_calcul.class);
        startActivity(intent);
    }

    public void historique(View view){
        Intent intent = new Intent(apprentissage.this, historique.class);
        startActivity(intent);
    }


}
