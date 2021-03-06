package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import android.os.Vibrator;
import android.content.Intent;


public class MainActivity<i> extends AppCompatActivity {

    List<Integer> options;
    List<String> usedoperators;
    List<Double> answers;
    ConstraintLayout bg;
    GridLayout grid1, grid2, grid3, grid4, grid5, grid6;
    Button submit;
    String[] operators = {"+", "-", "x", "/"};
    String selectedAnswer;
    int lives = 3, score = 0, highscore=0;
    Vibrator vibe;

    SharedPreferences pref1;
    

    public void createPuzzle() {

        options = new ArrayList<Integer>();
        usedoperators = new ArrayList<String>();
        answers = new ArrayList<Double>();

        for (int i = 0; i < 10; i++) {
            int rand = new Random().nextInt(30)+1;
            while (options.contains(rand)){
                rand = new Random().nextInt(30)+1;
            }
            options.add(rand);
        }
        for (int i = 0; i < grid2.getChildCount(); i++) {
            TextView cell2 = (TextView) grid2.getChildAt(i);
            int randind = new Random().nextInt(4);
            cell2.setText(operators[randind]);
            usedoperators.add(operators[randind]);
        }

        for (int i = 0; i < 5; ++i) {
            double operand1 = options.get(2 * i);
            double operand2 = options.get((2 * i) + 1);
            if (usedoperators.get(i) == "+") {
                double sum = operand1 + operand2;
                answers.add(sum);
            } else if (usedoperators.get(i) == "-") {
                double diff = operand1 - operand2;
                answers.add(diff);
            } else if (usedoperators.get(i) == "x") {
                double prod = operand1 * operand2;
                answers.add(prod);
            } else if (usedoperators.get(i) == "/") {
                double quo = operand1 / operand2;
                answers.add(quo);
            }

        }
        Collections.shuffle(options);
        for (int i = 0; i < grid6.getChildCount(); i++) {
            Button cell6 = (Button) grid6.getChildAt(i);
            cell6.setText(String.valueOf(options.get(i)));
            cell6.setTag(String.valueOf(options.get(i)));
            cell6.setBackgroundColor(Color.GRAY);
        }
        for (int i = 0; i < grid5.getChildCount(); i++) {
            TextView cell5 = (TextView) grid5.getChildAt(i);
            cell5.setText(String.valueOf(answers.get(i)));
            DecimalFormat form = new DecimalFormat("0.00");
            double x=Double.parseDouble(String.valueOf(cell5.getText()));
            cell5.setText(form.format(x));

        }
        for (int i = 0; i < grid1.getChildCount(); i++) {
            Button cell1 = (Button) grid1.getChildAt(i);
            cell1.setText("");
        }
        for (int i = 0; i < grid3.getChildCount(); i++) {
            TextView cell3 = (TextView) grid3.getChildAt(i);
            cell3.setText("");
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        grid1 = (GridLayout) findViewById(R.id.grid1);
        grid2 = (GridLayout) findViewById(R.id.grid2);
        grid3 = (GridLayout) findViewById(R.id.grid3);
        grid4 = (GridLayout) findViewById(R.id.grid4);
        grid5 = (GridLayout) findViewById(R.id.grid5);
        grid6 = (GridLayout) findViewById(R.id.grid6);
        bg = (ConstraintLayout)findViewById(R.id.bg);
        vibe=(Vibrator)this.getSystemService(VIBRATOR_SERVICE);
        submit=(Button)findViewById(R.id.submit);

        pref1 = getSharedPreferences("hackermode",Context.MODE_PRIVATE);


        createPuzzle();
    }


    boolean flag = false;

    public void highlight(View view) {
        if (!flag) {
            view.setBackgroundColor(Color.WHITE);
            Button button = (Button) view;
            selectedAnswer = (String) button.getText();
            for (int i = 0; i < grid1.getChildCount(); i++) {
                Button cell1 = (Button) grid1.getChildAt(i);
                cell1.setClickable(true);
            }
            for (int i = 0; i < grid3.getChildCount(); i++) {
                Button cell3 = (Button) grid3.getChildAt(i);
                cell3.setClickable(true);
            }
            for(int i=0;i<grid6.getChildCount();i++){
                Button cell6 = (Button) grid6.getChildAt(i);
                if (String.valueOf((cell6.getTag())) != selectedAnswer){
                    cell6.setClickable(false);
                }
            }
            flag=true;
        }
        else{
            view.setBackgroundColor(Color.GRAY);
            for (int i = 0; i < grid1.getChildCount(); i++) {
                Button cell1 = (Button) grid1.getChildAt(i);
                cell1.setClickable(false);
            }
            for (int i = 0; i < grid3.getChildCount(); i++) {
                Button cell3 = (Button) grid3.getChildAt(i);
                cell3.setClickable(false);
            }
            flag=false;
        }

    }
    public void txtfill(View view){
        Button button = (Button) view;
        if (flag){
            if (String.valueOf(button.getText()) == "") {
                button.setText(selectedAnswer);
                for (int i = 0; i < grid6.getChildCount(); i++) {
                    Button cell6 = (Button) grid6.getChildAt(i);
                    if (String.valueOf((cell6.getText())) == selectedAnswer) {
                        cell6.setText("");
                    }
                    cell6.setClickable(true);
                }
            }
            flag=false;
        }
        else {
            selectedAnswer = String.valueOf(button.getText());
            button.setText("");
            for (int i = 0; i < grid6.getChildCount(); i++) {
                Button cell6 = (Button) grid6.getChildAt(i);
                if (String.valueOf((cell6.getTag())) == selectedAnswer) {
                    cell6.setText(selectedAnswer);
                    cell6.setBackgroundColor(Color.GRAY);
                }
                cell6.setClickable(true);
            }
            selectedAnswer = "";
        }
    }

    public void submit (View view){
        int x=0;
        DecimalFormat decimalFormat = new DecimalFormat ("0.00");


        for (int i =0;i<5;i++){
            Button cell1 = (Button)grid1.getChildAt(i);
            TextView cell2 = (TextView) grid2.getChildAt(i);
            Button cell3 = (Button)grid3.getChildAt(i);
            TextView cell5 = (TextView) grid5.getChildAt(i);

            double op1 = Double.parseDouble(String.valueOf(cell1.getText()));
            double op2 = Double.parseDouble(String.valueOf(cell3.getText()));
            double sol = Double.parseDouble(String.valueOf(cell5.getText()));
            String op = String.valueOf(cell2.getText());


            if (op =="+"){
                if (op1+op2 == sol){
                    x+=1;
                }
            }
            if (op =="-"){
                if (op1-op2 == sol){
                    x+=1;
                }
            }
            if (op =="x"){
                if (op1*op2 == sol){
                    x+=1;
                }
            }
            if (op =="/"){
                DecimalFormat form = new DecimalFormat("0.00");
                double y=Double.parseDouble(form.format(op1/op2));
                if (y == sol){
                    x+=1;
                }
            }
        }

        if (x!=5 && lives>0){
            lives-=1;
            score+=x*100;
            createPuzzle();
            Toast.makeText(getApplicationContext(), "You have " + String.valueOf(lives) + " lives remaining.", Toast.LENGTH_SHORT).show();
        }
        else {
            score+=x*100;
            Toast.makeText(getApplicationContext(), "Your score is " + String.valueOf(score), Toast.LENGTH_SHORT).show();
            createPuzzle();
        }

        if (lives==0){
            if(score>highscore){
                highscore=score;
            }
            Toast.makeText(getApplicationContext(), "Your score is " + String.valueOf(score), Toast.LENGTH_SHORT).show();
            lives=3;
            score=0;
            vibe.vibrate(500);
            createPuzzle();
        }
    }

    boolean sflag;
    public void settheme (View view){
        if (!sflag){
            bg.setBackgroundColor(Color.BLACK);
            for (int i=0;i<5;i++){
                TextView cell2 = (TextView) grid2.getChildAt(i);
                TextView cell4 = (TextView) grid4.getChildAt(i);
                TextView cell5 = (TextView) grid5.getChildAt(i);

                cell2.setTextColor(Color.WHITE);
                cell4.setTextColor(Color.WHITE);
                cell5.setTextColor(Color.WHITE);
            }
        }
        else {
            bg.setBackgroundColor(Color.WHITE);
            for (int i=0;i<5;i++){
                TextView cell2 = (TextView) grid2.getChildAt(i);
                TextView cell4 = (TextView) grid4.getChildAt(i);
                TextView cell5 = (TextView) grid5.getChildAt(i);

                cell2.setTextColor(Color.BLACK);
                cell4.setTextColor(Color.BLACK);
                cell5.setTextColor(Color.BLACK);
            }
        }
    }

    public void theme(View view){
        Switch theme = (Switch) view;
        if(!sflag){
            theme.setText("Light Mode");
            theme.setTextColor(Color.WHITE);
            settheme(bg);
            sflag=true;
        }
        else{
            theme.setText("Dark Mode");
            theme.setTextColor(Color.BLACK);
            settheme(bg);
            sflag=false;
        }
    }
    public void gotohome(View view){
        Intent homeintent = new Intent(this,homepage.class);
        pref1.edit().putBoolean("theme",sflag).apply();
        startActivity(homeintent);
    }
    public void gotosp(View view){
        Intent scoreintent = new Intent(this,scorepage.class);
        pref1.edit().putString("highscore",Integer.toString(highscore)).apply();
        pref1.edit().putBoolean("theme",sflag).apply();
        startActivity(scoreintent);
    }
}