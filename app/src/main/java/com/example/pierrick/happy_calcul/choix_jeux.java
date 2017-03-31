package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class choix_jeux extends AppCompatActivity {

    private ReadXMLFileCurrentUser profilUser;
    private ReadXMLFileProfil profil;
    public static profil profil1 = new profil();
    private redondance tableauRedondance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_jeux);

        TextView prenom = (TextView) findViewById(R.id.textView_pres);
        prenom.setText(connexion.current.getName());


        InputStream is = null;
        InputStream is2 = null;

        try {
            AssetManager am = this.getAssets();
            is = am.open(connexion.current.getName()+".xml");



        }catch(IOException ex) {
            //Do something witht the exception
        }


        File f = new File(this.getFilesDir(), connexion.current.getName()+".xml");
        profilUser = new ReadXMLFileCurrentUser(f);

        try {
            AssetManager am = this.getAssets();
            is = am.open(connexion.current.getLevel()+".xml");



        }catch(IOException ex) {
            //Do something witht the exception
        }
        profil = new ReadXMLFileProfil(is, connexion.current.getNumLevel());
        profil1 = profil.getProfil1();

        if(connexion.firstConnexion || resultat.changementLevel){

            tableauRedondance = new redondance(profil1.getBorneDeuxiemeDigitDigit());//utile pour la redondance des calculs

            ecrireFichierRedondance(profil1.getBorneDeuxiemeDigitDigit(),this, tableauRedondance.tableauCalcul);

            connexion.firstConnexion = false;
        }



    }







    public void pageTables(View view){
        Intent intent = new Intent(choix_jeux.this, tables.class);
        startActivity(intent);
    }


    public void jouer(View view){
        Intent intent = new Intent(choix_jeux.this, page_calcul.class);
        startActivity(intent);
    }

    public void historique(View view){
        Intent intent = new Intent(choix_jeux.this, historique.class);
        startActivity(intent);
    }



    public void logout(View view){
        connexion.firstConnexion=true;
        Intent intent = new Intent(this, connexion.class);
        startActivity(intent);
    }

    public profil getProfil1(){
        return profil1;
    }




    public void ecrireFichierRedondance(int nbTables, Context context, int[][] tableauCalcul){

        try {
            File path = context.getFilesDir();
            File file = new File(path, "redondance.txt");
            FileOutputStream stream = new FileOutputStream(file);
            int nbLignes = (int)(0.5 * nbTables * (nbTables+1));
            for(int i = 1 ; i<= nbTables ; i++)
                for(int j = i ; j <= nbTables; j++)
                {
                    stream.write(tableauCalcul[i][j]);
                    stream.write(System.getProperty("line.separator").getBytes());
                }



            stream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }


    }

}