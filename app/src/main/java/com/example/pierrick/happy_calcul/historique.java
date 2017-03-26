package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class historique extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        TextView utilisateur = (TextView) findViewById(R.id.textView_historique);
        utilisateur.setText(listeUtilisateurs.current.getName().toUpperCase());
    }

    public void delete(View view) {
        File f = new File(this.getFilesDir(), "users.xml");
        File f2 = new File(this.getFilesDir(),listeUtilisateurs.current.getName() + ".xml");

        WriteXMLFileUsers fileUsers;
        fileUsers = new WriteXMLFileUsers(f);
        fileUsers.delete(listeUtilisateurs.current.getName());
        f2.delete();

        ReadXMLFileUsers file;
        file = new ReadXMLFileUsers(f);

        Log.e("youpi","j'ai reussi ma vie" + listeUtilisateurs.current.getName());

        Intent intent = new Intent(historique.this, listeUtilisateurs.class);
        startActivity(intent);
    }

    public void previous(View view){
        Context context = this;

        button b = new button();
        b.previous(context);

    }
}
