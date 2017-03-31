package com.example.pierrick.happy_calcul;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

    public static ReadXMLFileUsers fileUsers;
    public static user current= new user();
    public static boolean firstConnexion = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        EditText pseudo = (EditText)findViewById(R.id.editText_pseudo);
        EditText mdp = (EditText)findViewById(R.id.editText_mdp);

        pseudo.requestFocus();

        writeToFile(1,this); //utile pour la page de calcul



        String test = "";
        InputStream is = null;


        try {
            AssetManager am = this.getAssets();
            is = am.open("users.xml");


        }catch(IOException ex) {
            //Do something witht the exception
        }


        File f2 = new File(this.getFilesDir(), "users.xml");
        fileUsers = new ReadXMLFileUsers(f2);
    }


    public void connexion(View view){
        EditText pseudo = (EditText) findViewById(R.id.editText_pseudo);
        EditText mdp = (EditText) findViewById(R.id.editText_mdp);

        boolean test = fileUsers.estPresent(pseudo.getText().toString(), mdp.getText().toString());
        int index = fileUsers.getPosition();

        if((pseudo.getText().toString().equals("admin"))&&(mdp.getText().toString().equals("admin")))
        {
            current = new user(pseudo.getText().toString(), mdp.getText().toString());
            Intent intent = new Intent(connexion.this, listeUtilisateurs.class);
            startActivity(intent);
        }
        if(test){

            current = new user(pseudo.getText().toString(), mdp.getText().toString());
            Intent intent = new Intent(connexion.this, choix_jeux.class);
            startActivity(intent);
        }
        else if (!(pseudo.getText().toString().equals("admin")))
            Toast.makeText(this, "Erreur de connexion", Toast.LENGTH_SHORT).show();


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

    public void quit(View view){

        new AlertDialog.Builder(this)
                .setTitle("Tu nous quittes déjà ?")
                .setMessage("Que voulez vous faire ? ")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        finishAffinity();

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.mipmap.logo)
                .show();

    }



}
