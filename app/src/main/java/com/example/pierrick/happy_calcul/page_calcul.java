package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class page_calcul extends AppCompatActivity {

    private int digit1 = 0;
    private int digit2 = 0;
    private int borneMin = 1;
    private int borneMax = 10;
    private int nombreDeQuestion = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_calcul);
        lancerJeux();


        final EditText resultat = (EditText) findViewById(R.id.editTextResultat);
        resultat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.e("TEST", "Enter pressed :" + resultat.getText().toString());
                }
                return false;
            }
        });

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

    public int nombreAleatoire(int borneMin, int borneMax){

        return (int)(Math.random()*(borneMax-borneMin));
    }

    public void lancerJeux(){

        TextView digitGauche = (TextView) findViewById(R.id.textViewDigitGauche);
        TextView digitDroite = (TextView) findViewById(R.id.textViewDigitDroite);
        TextView numeroQuestion = (TextView) findViewById(R.id.textViewNombreQuestion);
        TextView nbQuestion = (TextView) findViewById(R.id.textViewNbQuestion);
        final EditText resultat = (EditText) findViewById(R.id.editTextResultat);

        //final boolean boutonPressed = false;

        nbQuestion.setText(" / " + String.valueOf(nombreDeQuestion));

        for(int i = 1; i <= nombreDeQuestion ; i ++ ){
            digit1 = nombreAleatoire(borneMin, borneMax);
            digit2 = nombreAleatoire(borneMin, borneMax);



            resultat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                        Log.e("TEST", "Enter pressed :" + resultat.getText().toString());
                    }
                    return false;
                }
            });


            digitGauche.setText(String.valueOf(digit1));
            digitDroite.setText(String.valueOf(digit2));
            numeroQuestion.setText(String.valueOf(i));

        }


    }
}

