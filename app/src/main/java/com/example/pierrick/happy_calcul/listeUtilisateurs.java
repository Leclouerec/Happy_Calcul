package com.example.pierrick.happy_calcul;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Iterator;

public class listeUtilisateurs extends AppCompatActivity {

    public static user current = new user("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_utilisateurs);



        current.setName("admin");

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.liste_utilisateurs);


        File f2 = new File(this.getFilesDir(), "users.xml");
        File f3 = new File(this.getFilesDir(), ".xml");
        f3.delete();
        connexion.fileUsers = new ReadXMLFileUsers(f2);
        int taille = ReadXMLFileUsers.users.size();

        final TextView[] myTextViews = new TextView[taille];

        for(int i = 0; i < taille; i++){
            final TextView currentTextView = new TextView(this);
            currentTextView.setText(ReadXMLFileUsers.users.get(i).getName());
            currentTextView.setId(View.generateViewId());
            currentTextView.setTextSize(30);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            if(i == 0){
                lp.addRule(RelativeLayout.BELOW,R.id.textView_utilisateurs);
                lp.addRule(RelativeLayout.START_OF,R.id.textView_utilisateurs);
                //lp.setMarginEnd(79);
                lp.setMargins(30,177,0,0);
            }
            else{
                lp.addRule(RelativeLayout.BELOW,myTextViews[i-1].getId());
                lp.addRule(RelativeLayout.ALIGN_START,myTextViews[i-1].getId());
                lp.setMargins(0,43,0,0);
            }

            currentTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    connexion.current.setName(currentTextView.getText().toString());
                    Intent intent = new Intent(listeUtilisateurs.this, historique.class);
                    startActivity(intent);
                }

            });

            rl.addView(currentTextView, lp);

            myTextViews[i] = currentTextView;
        }


    }


    public void add(View view){
        Intent intent = new Intent(listeUtilisateurs.this, new_user.class);
        startActivity(intent);
    }

    public void logout(View view){
        connexion.firstConnexion=true;
        current.setName("");
        Intent intent = new Intent(this, connexion.class);
        startActivity(intent);
    }

}
