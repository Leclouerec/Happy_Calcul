package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class historique extends AppCompatActivity {

    private static List<partie> histo = new ArrayList<partie>();
    public String current = "";
    public static partie currentPartie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        ImageButton b = (ImageButton) findViewById(R.id.imageButton_trash);
        if(!(listeUtilisateurs.current.getName().equals("admin")))
            b.setVisibility(View.INVISIBLE);


        TextView utilisateur = (TextView) findViewById(R.id.textView_historique);
        utilisateur.setText(connexion.current.getName().toUpperCase());

        File f2 = new File(this.getFilesDir(),connexion.current.getName() + "historique.xml");
        ReadXMLFileCurrentUser file = new ReadXMLFileCurrentUser();
        histo = file.historique(f2);

        for (Iterator<partie> i = histo.iterator(); i.hasNext();
                ) {
            partie item = i.next();
            System.out.println("historique "+ item);
        }

        afficheHistorique();



        if(listeUtilisateurs.current.getName().equals("admin")){
            File f3 = new File(this.getFilesDir(), connexion.current.getName()+".xml");
            ReadXMLFileCurrentUser profilUser;
            profilUser = new ReadXMLFileCurrentUser(f3);

            TextView detail = (TextView)findViewById(R.id.textView_detail);
            detail.setText("Profil : " + connexion.current.getLevel() + " , au niveau : " + connexion.current.getNumLevel() + " ,série en cours : " + connexion.current.getSerie());
        }



    }

    public void delete(View view) {
        File f = new File(this.getFilesDir(), "users.xml");
        File f2 = new File(this.getFilesDir(),connexion.current.getName() + ".xml");
        File f3 = new File(this.getFilesDir(),connexion.current.getName() + "historique.xml");


        WriteXMLFileUsers fileUsers;
        fileUsers = new WriteXMLFileUsers(f);
        fileUsers.delete(connexion.current.getName());
        f2.delete();
        f3.delete();

        ReadXMLFileUsers file;
        connexion.fileUsers = new ReadXMLFileUsers(f);

        Log.e("youpi","j'ai reussi ma vie" + listeUtilisateurs.current.getName());

        Intent intent = new Intent(historique.this, listeUtilisateurs.class);
        startActivity(intent);
    }

    public void previous(View view){
        Context context = this;

        button b = new button();
        b.previous(context);

    }

    public void afficheHistorique(){

        int taille = histo.size();
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.historique);

        final TextView[] myTextViews = new TextView[taille];

        for(int i = taille-1; i >=0; i--){
            final int compteur = i;
            final TextView currentTextView = new TextView(this);
            currentTextView.setText(histo.get(i).getJour() + " à " + histo.get(i).getHeure() + " avec un score de " + histo.get(i).getRes() + "/20.");
            currentTextView.setId(View.generateViewId());
            currentTextView.setTextSize(30);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if(i == taille -1){
                lp.addRule(RelativeLayout.BELOW,R.id.textView_utilisateurs);
                lp.addRule(RelativeLayout.START_OF,R.id.textView_utilisateurs);
                //lp.setMarginEnd(79);
                lp.setMargins(30,177,0,0);
            }
            else{
                lp.addRule(RelativeLayout.BELOW,myTextViews[i+1].getId());
                lp.addRule(RelativeLayout.ALIGN_START,myTextViews[i+1].getId());
                lp.setMargins(0,43,0,0);
            }

            currentTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.e("CLICK", " test" + currentTextView.getText().toString());
                    currentPartie = new partie(histo.get(compteur).getJour(),histo.get(compteur).getHeure(),histo.get(compteur).getRes());
                    //Intent intent = new Intent(listeUtilisateurs.this, apprentissage.class);
                    //startActivity(intent);
                    current = currentTextView.getText().toString();
                    Intent intent = new Intent(historique.this, historique_detaille.class);
                    startActivity(intent);
                }

            });

            rl.addView(currentTextView, lp);

            myTextViews[i] = currentTextView;
        }
    }
}
