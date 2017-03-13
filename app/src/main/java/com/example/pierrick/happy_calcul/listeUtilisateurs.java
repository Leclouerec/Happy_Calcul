package com.example.pierrick.happy_calcul;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class listeUtilisateurs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_utilisateurs);

        Button myButton = new Button(this);
        myButton.setText("push");
        myButton.setBackgroundColor(Color.TRANSPARENT);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.activity_liste_utilisateurs);


        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //lp2.addRule(RelativeLayout.ALIGN_START, R.id.);

        //FrameLayout fl = (FrameLayout)findViewById(R.id.frameLayout_utilisateurs);
        TextView text = new TextView(this);
        TextView text2 = new TextView(this);
        TextView text3 = new TextView(this);

        text.setId(View.generateViewId());
        text2.setId(View.generateViewId());
        text3.setId(View.generateViewId());
        text.setText("ON va essayer ");
        text2.setText("un autre alaz o e fsdofsd^fk spf ks ^k sf s^ ");
        text3.setText("un autre alaz o e fsdofsd^fk spf s^ ");
        text.setTextSize(30);
        text2.setTextSize(30);
        text3.setTextSize(30);


        lp.addRule(RelativeLayout.BELOW,R.id.textView_utilisateurs);
        lp.addRule(RelativeLayout.START_OF,R.id.textView_utilisateurs);
        //lp.setMarginEnd(79);
        lp.setMargins(30,177,0,0);


        lp2.addRule(RelativeLayout.BELOW,text.getId());
        lp2.addRule(RelativeLayout.ALIGN_START,text.getId());
        lp2.setMargins(0,43,0,0);


        lp3.addRule(RelativeLayout.BELOW,text2.getId());
        lp3.addRule(RelativeLayout.ALIGN_START,text2.getId());
        lp3.setMargins(0,43,0,0);

        rl.addView(text, lp);
        rl.addView(text2, lp2);
        rl.addView(text3, lp3);
        //fl.addView(text, lp);
        //fl.addView(text2, lp);

    }
}
