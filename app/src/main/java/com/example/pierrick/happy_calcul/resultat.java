package com.example.pierrick.happy_calcul;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class resultat extends AppCompatActivity {

    public static String resGauche = "";
    public static String resDroite = "";
    public static int resulat=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);


        Log.e("ddsdsd", " dede " + resGauche);

        TextView gauche = (TextView) findViewById(R.id.textView_resultat_gauche);
        TextView droite = (TextView) findViewById(R.id.textView_resultat_droite);
        TextView res = (TextView) findViewById(R.id.textView_score_final);

        gauche.setText(Html.fromHtml(resGauche));
        droite.setText(Html.fromHtml(resDroite));
        Log.e("resultat " , " " + resulat );
        res.setText(resulat + " / 20");


    }

    public void test(View view){


        resGauche="";
        resDroite="";
        resulat = 0;

        button b = new button();
        b.home(view, this);
    }

}
