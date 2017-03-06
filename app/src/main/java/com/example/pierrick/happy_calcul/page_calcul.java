package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class page_calcul extends AppCompatActivity {

    private int digit1 = 0;
    private int digit2 = 0;
    private int borneMin = 1;
    private int borneMax = 10;  //sera dans les parametres du xml
    private int nombreDeQuestion = 20;//sera dans les paametres du xml;
    private int numeroQuestion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_calcul);
        //lancerJeux();

        initialisation();




        final EditText resultat = (EditText) findViewById(R.id.editTextResultat);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(resultat, InputMethodManager.SHOW_FORCED);

        if(resultat.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        resultat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.e("TEST", "Enter pressed :" + resultat.getText().toString());
                    valideCalcul();
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

        return (int)(Math.random()*(borneMax-1-borneMin))+1;
    }



    public void valideCalcul(){
        final EditText resultat = (EditText) findViewById(R.id.editTextResultat);
        TextView digitGauche = (TextView) findViewById(R.id.textViewDigitGauche);
        TextView digitDroite = (TextView) findViewById(R.id.textViewDigitDroite);

        String result = resultat.getText().toString();

        resultat.setText(null);

        int gauche =Integer.parseInt(digitGauche.getText().toString());
        int droite =Integer.parseInt(digitDroite.getText().toString());

        Context context = this;

        if (Integer.parseInt(result) == gauche * droite)
            Toast.makeText(context, "Resultat : Correct", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Resultat : FAUX t'es nul", Toast.LENGTH_SHORT).show();


        if(resultat.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(resultat, InputMethodManager.SHOW_FORCED);


        prochaineQuestion();


    }

    public void prochaineQuestion(){

        TextView digitGauche = (TextView) findViewById(R.id.textViewDigitGauche);
        TextView digitDroite = (TextView) findViewById(R.id.textViewDigitDroite);
        TextView numeroQuestionTV = (TextView) findViewById(R.id.textViewNombreQuestion);
        TextView nbQuestion = (TextView) findViewById(R.id.textViewNbQuestion);
        EditText resultat = (EditText) findViewById(R.id.editTextResultat);

        nbQuestion.setText(" / " + String.valueOf(nombreDeQuestion));

        resultat.requestFocus();

        numeroQuestionTV.setText(String.valueOf(++numeroQuestion));


        digit1 = nombreAleatoire(borneMin, borneMax);
        digit2 = nombreAleatoire(borneMin, borneMax);

        digitGauche.setText(String.valueOf(digit1));
        digitDroite.setText(String.valueOf(digit2));



        resultat.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(resultat, InputMethodManager.SHOW_FORCED);



    }


    public void initialisation(){

        TextView digitGauche = (TextView) findViewById(R.id.textViewDigitGauche);
        TextView digitDroite = (TextView) findViewById(R.id.textViewDigitDroite);

        TextView nbQuestion = (TextView) findViewById(R.id.textViewNbQuestion);
        TextView numeroQuestionTV = (TextView) findViewById(R.id.textViewNombreQuestion);


        digit1 = nombreAleatoire(borneMin, borneMax);
        digit2 = nombreAleatoire(borneMin, borneMax);

        nbQuestion.setText(" / " + String.valueOf(nombreDeQuestion));

        digitGauche.setText(String.valueOf(digit1));
        digitDroite.setText(String.valueOf(digit2));
        numeroQuestionTV.setText(String.valueOf(numeroQuestion));



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

