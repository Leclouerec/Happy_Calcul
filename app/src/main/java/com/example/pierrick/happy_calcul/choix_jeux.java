package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class choix_jeux extends AppCompatActivity {

    private int i = 0;
    private ReadXMLFileCurrentUser profilUser;
    private ReadXMLFileProfil profil;
    public static profil profil1 = new profil();
    private redondance tableauRedondance;
    private boolean firstConnexion = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_jeux);




        InputStream is = null;
        InputStream is2 = null;

        try {
            AssetManager am = this.getAssets();
            is = am.open(connexion.current.getName()+".xml");



        }catch(IOException ex) {
            //Do something witht the exception
        }


        profilUser = new ReadXMLFileCurrentUser(is);

        try {
            AssetManager am = this.getAssets();
            is = am.open(connexion.current.getLevel()+".xml");



        }catch(IOException ex) {
            //Do something witht the exception
        }
        Log.e("numLevel", " alazaz" + connexion.current.getNumLevel());
        profil = new ReadXMLFileProfil(is, connexion.current.getNumLevel());
        profil1 = profil.getProfil1();

        if(connexion.firstConnexion || resultat.changementLevel){

            Log.e("bornepremier", "fdpdigit" + profil1.getBornePremiereDigit());
            tableauRedondance = new redondance(profil1.getBornePremiereDigit());//utile pour la redondance des calculs

            ecrireFichierRedondance(profil1.getBornePremiereDigit(),this, tableauRedondance.tableauCalcul);

            connexion.firstConnexion = false;
        }



        /*tableauRedondance = new redondance(10);

        ecrireFichierRedondance(10,this, tableauRedondance.tableauCalcul);*/


        Toast.makeText(this, "Pseudo " + connexion.current.getName(), Toast.LENGTH_SHORT).show();
        Log.e("profil complet", "level :" + connexion.current.getLevel() + " ,numLevel : " + connexion.current.getNumLevel() + " ,serie : " + connexion.current.getSerie() + " , pourcentage : " + connexion.current.getPourcentage());

        Log.e("profil ", "bornepremiere : " + profil1.getBornePremiereDigit());

       /* final ImageView image = (ImageView) findViewById(R.id.imageView_chrono);
        //int i = 0;
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                i++;
                switch (i){
                    case 1 :image.setImageResource(R.drawable.chrono_8);
                        break;
                    case 2 :image.setImageResource(R.drawable.chrono_7);
                        break;
                    case 3 :image.setImageResource(R.drawable.chrono_6);
                        break;
                    case 4 :image.setImageResource(R.drawable.chrono_5);
                        break;
                    case 5 :image.setImageResource(R.drawable.chrono_4);
                        break;
                    case 6 :image.setImageResource(R.drawable.chrono_3);
                        break;
                    case 7 :image.setImageResource(R.drawable.chrono_2);
                        break;
                    case 8 :image.setImageResource(R.drawable.chrono_1);
                        break;
                    case 9 :image.setImageResource(R.drawable.chrono_9);
                        i=0;

                        break;
                    default : break;
                }

                handler.postDelayed(this, 1000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 1000); //for initial delay..*/



        Toast.makeText(this, "Pseudo " + connexion.current.getName(), Toast.LENGTH_SHORT).show();

    }







    public void pageTables(View view){
        Intent intent = new Intent(choix_jeux.this, tables.class);
        startActivity(intent);
    }


    public void jouer(View view){
        Intent intent = new Intent(this, apprentissage.class);
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
                    Log.e("gueuel du int", "  " + tableauCalcul[i][j]);
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