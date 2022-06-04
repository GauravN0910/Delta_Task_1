package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.TextView;

public class scorepage extends AppCompatActivity {

    TextView high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorepage);
        TextView high = (TextView) findViewById(R.id.high);
        Intent mainintent = getIntent();
        String highinput = mainintent.getStringExtra("key1");
        high.setText(highinput);
    }

    public void gotomain(View view){
        Intent mainintent = new Intent(this,MainActivity.class);
        startActivity(mainintent);
    }



}