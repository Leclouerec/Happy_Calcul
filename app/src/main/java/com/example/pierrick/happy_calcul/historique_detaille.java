package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class historique_detaille extends AppCompatActivity {

    private partie currentPartie;
    private List<calcul> calculs = new ArrayList<calcul>();
    private String resGauche ="";
    private String resDroite ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_detaille);

        currentPartie = new partie(historique.currentPartie.getJour(),historique.currentPartie.getHeure(),historique.currentPartie.getRes());

        File f = new File(this.getFilesDir(),currentPartie.getJour() + currentPartie.getHeure()+ ".xml");
        ReadXMLFileHistorique file = new ReadXMLFileHistorique(f);
        calculs = file.read();

        for (Iterator<calcul> i = calculs.iterator(); i.hasNext();
                ) {
            calcul item = i.next();
        }


        TextView gauche = (TextView) findViewById(R.id.textView_historique_gauche);
        TextView droite = (TextView) findViewById(R.id.textView_historique_droite);
        TextView res = (TextView) findViewById(R.id.textView_score);

        maj();

        if(listeUtilisateurs.current.getName().equals("admin")){
            ImageButton b = (ImageButton)findViewById(R.id.imageButton_home);
            b.setVisibility(View.INVISIBLE);
        }


        gauche.setText(Html.fromHtml(resGauche));
        droite.setText(Html.fromHtml(resDroite));
        res.setText(historique.currentPartie.getRes() + " / 20");
    }


    private void maj(){

        boolean juste = false;
        int res = 0 ;
        int calcul = 0;

        for(int i =1 ; i<=20 ; i++){

            res = calculs.get(i-1).getResultat();
            calcul = calculs.get(i-1).getGauche() * calculs.get(i-1).getDroite();
            juste = (res == calcul);

            if(i <=10)
                if(juste)
                    resGauche += "\n" +"<br>"+calculs.get(i-1).getGauche() + " X " + calculs.get(i-1).getDroite() + " = " + " <font color=#29E830>"+res+"</font>";
                else
                    resGauche += "\n" +"<br>"+ calculs.get(i-1).getGauche() + " X " + calculs.get(i-1).getDroite() + " = " + " <font color=#cc0029>"+res+"</font>" + " ("+calcul+") ";
            else
                if(juste)
                    resDroite += "\n" +"<br>"+ calculs.get(i-1).getGauche() + " X " + calculs.get(i-1).getDroite() + " = " + " <font color=#29E830>"+res+"</font>";
                else
                    resDroite += "\n"+"<br>" + calculs.get(i-1).getGauche() + " X " + calculs.get(i-1).getDroite() + " = " +" <font color=#cc0029>"+ res+"</font>"+" ("+calcul+") ";

        }


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
}
