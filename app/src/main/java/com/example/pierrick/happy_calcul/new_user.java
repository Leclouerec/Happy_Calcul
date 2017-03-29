package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class new_user extends AppCompatActivity{


    private Spinner spinner_profil;
    private WriteXMLFileUsers fileUsers;
    private WriteXMLFileNewUser fileNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        spinner_profil = (Spinner) findViewById(R.id.spinner_profil);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.profil, R.layout.spinner_item);
        spinner_profil.setAdapter(adapter);

    }



    public void addListenerOnSpinnerItemSelection() {
        spinner_profil = (Spinner) findViewById(R.id.spinner_profil);
        spinner_profil.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void add(View view){


        EditText name = (EditText) findViewById(R.id.editText_prenom);
        EditText mdp = (EditText) findViewById(R.id.editText_new_mdp);
        EditText confirmation = (EditText) findViewById(R.id.editText_confirmation);

        Spinner mySpinner=(Spinner) findViewById(R.id.spinner_profil);

        boolean correct = false;
        boolean existant = false;

        if (mdp.getText().toString().equals(confirmation.getText().toString()))
            correct = true;
        if(estPresent(ReadXMLFileUsers.users, name.getText().toString())){
            existant = true;
            correct = false;
            Toast.makeText(this, "Pseudo déjà existant", Toast.LENGTH_SHORT).show();
        }
        if(name.getText().toString().equals("admin")){
            correct = false;
            existant = true;
            Toast.makeText(this, "Pseudo interdit", Toast.LENGTH_SHORT).show();
        }



        File f = new File(this.getFilesDir(), "users.xml");
        File f2 = new File(this.getFilesDir(),name.getText().toString() +".xml");

        File[] files = this.getFilesDir().listFiles();
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
        }

        if(correct){

            //ajout ou création du fichier de login
            if(f.exists()){
                fileUsers = new WriteXMLFileUsers(f);
                fileUsers.addUser(new user(name.getText().toString(), mdp.getText().toString()));
                fileUsers.read();
                //f.delete();

            } else{
                fileUsers = new WriteXMLFileUsers(f);
                fileUsers.newUsers(new user(name.getText().toString(), mdp.getText().toString()));
                //fileUsers.read();
            }

            //création du fichier de l'utilisateur
            fileNewUser = new WriteXMLFileNewUser(f2);
            fileNewUser.newUser(new user(name.getText().toString(), mdp.getText().toString(),mySpinner.getSelectedItem().toString()));
            fileNewUser.read();

        }
        else if(!existant){
            Toast.makeText(this, "Confirmation de mot de passe erroné", Toast.LENGTH_SHORT).show();

        }

        if(!existant && correct){
            Intent intent = new Intent(new_user.this, listeUtilisateurs.class);
            startActivity(intent);
        }


    }


    public boolean estPresent(List<user> users, String _pseudo){

        for (Iterator<user> i = users.iterator(); i.hasNext();
                ) {
            user item = i.next();
            if((item.getName().equals( _pseudo))){
                Log.e("POURQIOI", " pseudo : "+item.getName() + " ,mdp : " +item.getMdp());
                return true;
            }


        }
        return false;

    }

    public void previous(View view){
        Context context = this;

        button b = new button();
        b.previous(context);

    }

}
