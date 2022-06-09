package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class scorepage extends AppCompatActivity {

    TextView high;
    ConstraintLayout score;
    SharedPreferences pref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);

        score = (ConstraintLayout)findViewById(R.id.score);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scorepage);


//        TextView high = (TextView) findViewById(R.id.high);
//        Intent mainintent = getIntent();
//        String highinput = mainintent.getStringExtra("highscore");
//        boolean theme = mainintent.getBooleanExtra("theme",false);
//        high.setText(highinput);

        pref1 = getSharedPreferences("hackermode", Context.MODE_PRIVATE);
        pref1.getBoolean("theme",false);
        pref1.getString("highscore","");


    }

    public void gotomain(View view){
        Intent mainintent = new Intent(this,MainActivity.class);
        startActivity(mainintent);
    }

}