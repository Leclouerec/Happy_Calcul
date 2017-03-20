package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class resultat extends AppCompatActivity {

    public static String resGauche = "";
    public static String resDroite = "";
    public static int resulat=0;
    public static boolean changementLevel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);



        TextView gauche = (TextView) findViewById(R.id.textView_resultat_gauche);
        TextView droite = (TextView) findViewById(R.id.textView_resultat_droite);
        TextView res = (TextView) findViewById(R.id.textView_score_final);

        gauche.setText(Html.fromHtml(resGauche));
        droite.setText(Html.fromHtml(resDroite));
        Log.e("resultat " , " " + resulat );
        Log.e("serie " , " " + connexion.current.getSerie() );
        res.setText(resulat + " / 20");


        updateProfil();


    }

    public void updateProfil(){
        if((resulat/0.2)>= page_calcul.getPourcentageSuivant()){
            connexion.current.setSerie(connexion.current.getSerie()+1);
        }
        else{
            connexion.current.setSerie(0);
        }

        Log.e("test serie ", "" + choix_jeux.profil1.getSerie());

        if((connexion.current.getSerie() == choix_jeux.profil1.getSerie())&&(choix_jeux.profil1.getSerie()!=0)){
            connexion.current.setNumLevel(connexion.current.getNumLevel()+1);
            changementLevel =true;
            connexion.current.setSerie(0);
        }


    }

    public void test(View view){

        resGauche="";
        resDroite="";
        resulat = 0;

        button b = new button();
        b.home(view, this);
    }

    private void writeToFile(int data, Context context, String chemin) {
        try {
            File path = context.getFilesDir();
            File file = new File(path, chemin);
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
