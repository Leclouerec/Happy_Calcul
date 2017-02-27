package com.example.pierrick.happy_calcul;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class choix_jeux extends AppCompatActivity {

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_jeux);


        final ImageView image = (ImageView) findViewById(R.id.imageView_chrono);
        //int i = 0;
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                i++;
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
                        break;
                    case 9 :image.setImageResource(R.drawable.chrono_9);
                        i=0;

                        break;
                    default : break;
                }

                handler.postDelayed(this, 1000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 1000); //for initial delay..

    }







    public void pageTables(View view){
        Intent intent = new Intent(choix_jeux.this, tables.class);
        startActivity(intent);
    }


    public void jouer(View view){
        Intent intent = new Intent(this, apprentissage.class);
        startActivity(intent);
    }


    public void test(View view){
        ImageView image = (ImageView) findViewById(R.id.imageView_chrono);
        i++;
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
                break;
            case 9 :image.setImageResource(R.drawable.chrono_9);
                i=0;
                break;
            default : break;
        }

    }

}