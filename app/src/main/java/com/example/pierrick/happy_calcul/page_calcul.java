package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

public class page_calcul extends AppCompatActivity {

    private int digit1 = 0;
    private int digit2 = 0;
    private int borneMin = 1;
    private int borneMax = 10;  //sera dans les parametres du xml
    private int nombreDeQuestion = 20;//sera dans les paametres du xml;
    private int numeroQuestion = 1;
    private int serie = 0; //nombre de serie pour passer au prochain profil
    private static int pourcentageSuivant = 0;
    private int pourcentage2x2 = 0;
    private redondance calcul;
    private int[][] tableauCalcul;
    private int gauche = 0;
    private int droite = 0;
    private profil profil1;
    private boolean test = false;


    public page_calcul(){}

    public static int getPourcentageSuivant() {
        return pourcentageSuivant;
    }

    public void setPourcentageSuivant(int pourcentageSuivant) {
        this.pourcentageSuivant = pourcentageSuivant;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_calcul);
        //lancerJeux();

        //calcul = new redondance(10);



        Log.e("JE PASSE QUAND", " MEME" + choix_jeux.profil1.getBornePremiereDigit());

        profil1 = choix_jeux.profil1;
        borneMin = profil1.getBornePremiereDigit();
        borneMax = profil1.getBorneDeuxiemeDigitDigit();//se sont les mêmes bornes ...
        serie = profil1.getSerie();
        pourcentageSuivant = profil1.getPourcentage();
        pourcentage2x2 = profil1.getPourcentage2x2();

        readRedondance(this);
        calcul = new redondance(borneMax);
        initialisation();
        Log.e("ET MEME", " LA");
        chronometre();

        //writeToFile(18,this);

        numeroQuestion = readFromFile(this); //recuperer le numero de la question sur un fichier externe car n recharge la page


        Log.e("ecrite et lecture", " de fichier " + readFromFile(this) + " ,numeroQuestion : "+numeroQuestion);

        TextView numeroQuestionTV = (TextView) findViewById(R.id.textViewNombreQuestion);
        numeroQuestionTV.setText(String.valueOf(numeroQuestion));


        final EditText resultat = (EditText) findViewById(R.id.editTextResultat);

        resultat.requestFocus();

        Log.e("moification serie", " STP " + serie);




        /*if(resultat.requestFocus()){
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
        });*/

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

        Random r = new Random();
        //return (int)(Math.random()*(borneMax-borneMin))+1;
        return (int)borneMin + r.nextInt(borneMax+1 - borneMin);
    }



    public void valideCalcul(){
        final EditText resultat = (EditText) findViewById(R.id.editTextResultat);
        TextView digitGauche = (TextView) findViewById(R.id.textViewDigitGauche);
        TextView digitDroite = (TextView) findViewById(R.id.textViewDigitDroite);


        LayoutInflater factory = getLayoutInflater();
        View resultatText = factory.inflate(R.layout.activity_resultat, null);
        TextView resultatGauche = (TextView) resultatText.findViewById(R.id.textView_resultat_gauche);
        TextView resultatDroite = (TextView) resultatText.findViewById(R.id.textView_resultat_droite);
        String resGauche = resultatGauche.getText().toString();
        String resDroite = resultatDroite.getText().toString();



        String result = resultat.getText().toString();

        Boolean juste;
        if(!test || (result.equals(""))){
            result ="0";
            Log.e("PUTAIN", " skcc");
        }


        if((Integer.parseInt(digitGauche.getText().toString())) <= (Integer.parseInt(digitDroite.getText().toString()))){
            gauche =Integer.parseInt(digitGauche.getText().toString());
            droite =Integer.parseInt(digitDroite.getText().toString());
        }
        else{
            droite =Integer.parseInt(digitGauche.getText().toString());
            gauche =Integer.parseInt(digitDroite.getText().toString());
        }

        Context context = this;

        try{
            if(Integer.parseInt(result) == gauche * droite){
                com.example.pierrick.happy_calcul.resultat.resultat++;
                Log.e("res", " " + com.example.pierrick.happy_calcul.resultat.resultat);
                juste = true;
            }
            else{
                juste = false;
            }

            if(numeroQuestion <=10)
                if(juste)
                    com.example.pierrick.happy_calcul.resultat.resGauche += "\n" +"<br>"+digitGauche.getText().toString() + " X " + digitDroite.getText().toString() + " = " + " <font color=#29E830>"+result+"</font>";
                else
                    com.example.pierrick.happy_calcul.resultat.resGauche += "\n" +"<br>"+ digitGauche.getText().toString() + " X " + digitDroite.getText().toString() + " = " + " <font color=#cc0029>"+result+"</font>" + " ("+(gauche * droite)+") ";
            else
            if(juste)
                com.example.pierrick.happy_calcul.resultat.resDroite += "\n" +"<br>"+ digitGauche.getText().toString() + " X " + digitDroite.getText().toString() + " = " + " <font color=#29E830>"+result+"</font>";
            else
                com.example.pierrick.happy_calcul.resultat.resDroite += "\n"+"<br>" + digitGauche.getText().toString() + " X " + digitDroite.getText().toString() + " = " +" <font color=#cc0029>"+ result+"</font>"+" ("+(gauche * droite)+") ";

            com.example.pierrick.happy_calcul.resultat.calculs.add(new calcul(Integer.parseInt(digitGauche.getText().toString()),Integer.parseInt(digitDroite.getText().toString()), Integer.parseInt(result)));
            resultat.setText(null);


            prochaineQuestion();
        }
        catch (NumberFormatException e) {
            Toast.makeText(context, "Nombre trop grand", Toast.LENGTH_SHORT).show();
        }



    }

    public void prochaineQuestion(){

        /*TextView digitGauche = (TextView) findViewById(R.id.textViewDigitGauche);
        TextView digitDroite = (TextView) findViewById(R.id.textViewDigitDroite);
        //TextView numeroQuestionTV = (TextView) findViewById(R.id.textViewNombreQuestion);
        TextView nbQuestion = (TextView) findViewById(R.id.textViewNbQuestion);
        EditText resultat = (EditText) findViewById(R.id.editTextResultat);

        nbQuestion.setText(" / " + String.valueOf(nombreDeQuestion));

        resultat.requestFocus();

        //numeroQuestionTV.setText(String.valueOf(++numeroQuestion));


        digit1 = nombreAleatoire(borneMin, borneMax);
        digit2 = nombreAleatoire(borneMin, borneMax);

        digitGauche.setText(String.valueOf(digit1));
        digitDroite.setText(String.valueOf(digit2));



        resultat.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(resultat, InputMethodManager.SHOW_FORCED);*/

        if(numeroQuestion != 20){
            writeToFile(numeroQuestion +1, this, "config.txt");

            tableauCalcul[gauche][droite]++;
            ecrireFichierRedondance(borneMax, this, tableauCalcul);

            finish();
            startActivity(getIntent());

        }else{

            writeToFile(1, this, "config.txt");

            Intent intent = new Intent(page_calcul.this, resultat.class);
            startActivity(intent);

        }




    }


    public void initialisation(){

        TextView digitGauche = (TextView) findViewById(R.id.textViewDigitGauche);
        TextView digitDroite = (TextView) findViewById(R.id.textViewDigitDroite);

        TextView nbQuestion = (TextView) findViewById(R.id.textViewNbQuestion);
        TextView numeroQuestionTV = (TextView) findViewById(R.id.textViewNombreQuestion);





        boolean aAjouter = false;
        int compteur = 0;
        do {

            int pourcent = (int)(Math.random() * (10-1)) + 1;//on tire un nombre aléatoire entre 1 et 10 pour calculer le pourcentage
            //de chance pour tirer un calcul 2x2
            Log.e("pourcent", " : " + pourcent + " pourcetnfat : " + pourcentage2x2/10);
            if (pourcent >= (100-pourcentage2x2)/10){
                digit1 = nombreAleatoire(10, borneMax);
                digit2 = nombreAleatoire(10, borneMax);
            }

            else{
                digit1 = nombreAleatoire(borneMin, borneMax);
                digit2 = nombreAleatoire(borneMin, borneMax);
            }


            if(digit1<=digit2)
                aAjouter = calcul.aAjouter(digit1,digit2, tableauCalcul);
            else
                aAjouter = calcul.aAjouter(digit2,digit1, tableauCalcul);
            if(compteur > 30)
                aAjouter = true;
            compteur++;
        }while (!aAjouter);




        //String test = "<font color=#cc0029>"+ " / " + String.valueOf(nombreDeQuestion)+"</font>";


        nbQuestion.setText(" / " + String.valueOf(nombreDeQuestion));

        digitGauche.setText(String.valueOf(digit1));
        digitDroite.setText(String.valueOf(digit2));
        numeroQuestionTV.setText(String.valueOf(numeroQuestion));



    }


    public void chronometre(){


        final ImageView image = (ImageView) findViewById(R.id.imageView_chrono);
        final EditText resultat = (EditText) findViewById(R.id.editTextResultat);
        //int i = 0;

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {

                if(resultat.requestFocus()){
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
                resultat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                            Log.e("TEST", "Enter pressed :" + resultat.getText().toString());
                            i=10;
                            test = true;
                            //valideCalcul();
                        }
                        return false;
                    }
                });
                i++;
                Log.e("gueuele de i", " "+i);
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
                        i=10;
                        break;
                    case 9 :image.setImageResource(R.drawable.chrono_9);
                        i=0;

                        break;
                    default :valideCalcul();return;
                }

                handler.postDelayed(this, 1000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 1000); //for initial delay..
        if(test)
            valideCalcul();



    }


    private void writeToFile(int data,Context context, String chemin) {
        try {
            File path = context.getFilesDir();
            File file = new File(path, chemin);
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    private int readFromFile(Context context) {


        String ret = "";
        File path = context.getFilesDir();
        File file = new File(path, "config.txt");
        int length = (int) file.length();

        Log.e("taille", " " + length);

        byte[] bytes = new byte[length];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();

        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Log.e("me soule", " " + bytes[0]);

        String contents = new String(bytes);
        int res = new BigInteger(bytes).intValue();

        return res;
    }

    private void readRedondance(Context context){
        String ret = "";
        File path = context.getFilesDir();
        File file = new File(path, "redondance.txt");
        int length = (int) file.length();
        tableauCalcul = new int[borneMax + 1][borneMax+1];

        Log.e("taille", " " + length);

        byte[] bytes = new byte[length];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();

        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        int k = 0;

        for(int i = 1 ; i<= borneMax ; i++)
            for(int j = i; j<=borneMax ; j++){
                tableauCalcul[i][j] = bytes[k];
                k+=2;
                Log.e("JESPERE CCEST BON", " "+tableauCalcul[i][j]);
            }



        Log.e("fait chier", " " + bytes[0] + " 1 :"  + bytes[1] + " 2 :" + bytes[2]);
    }

    public void ecrireFichierRedondance(int nbTables, Context context, int[][] tableauCalcul){

        Log.e("creation table", " brne " +nbTables);

        try {
            File path = context.getFilesDir();
            File file = new File(path, "redondance.txt");
            FileOutputStream stream = new FileOutputStream(file);
            int nbLignes = (int)(0.5 * nbTables * (nbTables+1));
            int k = 1;
            for(int i = 1 ; i<= nbTables ; i++)
                for(int j = i ; j <= nbTables; j++)
                {
                    Log.e("fait CHIER", " index  "+k + " ,valeur : " + tableauCalcul[i][j]);
                    k++;
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

