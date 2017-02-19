package com.example.pierrick.happy_calcul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by lol on 17/02/17.
 */
public class button {

    public void previous(Context context){

        Activity activity = (Activity) context;

        activity.finish();
    }


    public void home(View view, Context context){

        Intent intent = new Intent(context, choix_jeux.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);

    }
}
