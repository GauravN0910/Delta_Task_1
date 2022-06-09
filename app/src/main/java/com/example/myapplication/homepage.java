package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class homepage extends AppCompatActivity {

    ConstraintLayout homepage;

    SharedPreferences pref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_homepage);

        pref1= getSharedPreferences("hackermode", Context.MODE_PRIVATE);

        homepage = (ConstraintLayout)findViewById(R.id.homepage);
        pref1.getBoolean("theme",false);

    }

    public void gotomain(View view){
        Intent mainintent=new Intent(this,MainActivity.class);
        startActivity(mainintent);
    }
}