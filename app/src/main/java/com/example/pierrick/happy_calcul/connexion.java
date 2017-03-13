package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class connexion extends AppCompatActivity {

    private ReadXMLFileUsers fileUsers;
    public static user current= new user();
    private redondance tableauRedondance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        EditText pseudo = (EditText)findViewById(R.id.editText_pseudo);
        EditText mdp = (EditText)findViewById(R.id.editText_mdp);
        //mdp.clearFocus();
        pseudo.requestFocus();

        writeToFile(1,this); //utile pour la page de calcul

        tableauRedondance = new redondance(10);//utile pour la redondance des calculs

        ecrireFichierRedondance(10,this, tableauRedondance.tableauCalcul);




        String test = "";
        InputStream is = null;
        File f = new File(this.getFilesDir(), "stp.xml");
        Log.e("fichier", " il est où " + f.toURI());


        String state = Environment.getExternalStorageState();
        Log.e("on peut", "ou pas " + Environment.MEDIA_MOUNTED.equals(state));

        /*File f = new File(Environment.getExternalStorageDirectory(), "/MYFOLDER");

        if(!f.exists()){
            f.mkdirs();
        }
        if (!f.mkdirs()) {
            Log.e(" AAAH", "Directory not created");
        }*/

        try {
            AssetManager am = this.getAssets();
            is = am.open("users.xml");




        }catch(IOException ex) {
            //Do something witht the exception
        }

        fileUsers = new ReadXMLFileUsers(is, f);
        Log.e("tesjt", " list " + fileUsers.getUsers().get(0).getName() + " mot de passe " + fileUsers.getUsers().get(0).getMdp());
    }


    public void connexion(View view){
        EditText pseudo = (EditText) findViewById(R.id.editText_pseudo);
        EditText mdp = (EditText) findViewById(R.id.editText_mdp);

        boolean test = fileUsers.estPresent(pseudo.getText().toString(), mdp.getText().toString());
        int index = fileUsers.getPosition();

        if(test){
            if(pseudo.getText().toString().equals("admin"))
            {
                current = new user(pseudo.getText().toString(), mdp.getText().toString());
                Intent intent = new Intent(connexion.this, listeUtilisateurs.class);
                startActivity(intent);
            }
            else{
                current = new user(pseudo.getText().toString(), mdp.getText().toString());
                Intent intent = new Intent(connexion.this, choix_jeux.class);
                startActivity(intent);
            }

        }
        else
            Toast.makeText(this, "Erreur de connexion", Toast.LENGTH_SHORT).show();


        Log.e("POSITION", " est present:  " + test + " position : " + index);




    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void writeToFile(int data,Context context) {
        try {
            File path = context.getFilesDir();
            File file = new File(path, "config.txt");
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
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
