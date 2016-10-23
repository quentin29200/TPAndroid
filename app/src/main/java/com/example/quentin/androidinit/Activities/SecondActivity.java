package com.example.quentin.androidinit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quentin.androidinit.Perso;
import com.example.quentin.androidinit.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);

        Intent i = getIntent();
        Perso p = i.getParcelableExtra("perso");

        LinearLayout current = (LinearLayout) findViewById(R.id.example);

        TextView txt = new TextView(getApplicationContext());
        txt.setText(p.toString());

        current.addView(txt);
    }
}
