package com.example.pierrick.happy_calcul;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class new_user extends AppCompatActivity{


    private Spinner spinner_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        spinner_profil = (Spinner) findViewById(R.id.spinner_profil);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.profil, R.layout.spinner_item);
        spinner_profil.setAdapter(adapter);

    }



    public void addListenerOnSpinnerItemSelection() {
        spinner_profil = (Spinner) findViewById(R.id.spinner_profil);
        spinner_profil.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

}
