package com.example.pierrick.happy_calcul;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class tables extends AppCompatActivity {

    private Spinner spinner_tables;
    private String itemSelect;
    private TextView tv;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        spinner_tables = (Spinner) findViewById(R.id.spinner_tables);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tables, R.layout.spinner_item);
        spinner_tables.setAdapter(adapter);
        tv = (TextView)findViewById(R.id.textView_table);

        //addListenerOnButton();

    }


    @Override
    protected void onStart() {
        super.onStart();


        spinner_tables.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                itemSelect = (String) spinner_tables.getSelectedItem();

                setTextViewTable(itemSelect);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setTextViewTable(String item){
        int num = Integer.parseInt(item);
        String res = "";

        for(int i = 1; i < 10 ; i++)
            res += item + " X " + i + " = " + num*i + "\n";

        tv.setText(res);
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
