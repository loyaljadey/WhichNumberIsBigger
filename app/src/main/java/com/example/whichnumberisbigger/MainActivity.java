package com.example.whichnumberisbigger;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewScore;
    private int score;
    private ConstraintLayout constraintLayoutMain;
    private Button button1;
    private int num1;
    private Button button2;
    private int num2;

    public static final int MAX_NUM = 1000;
    public static final int MIN_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking the xml to the java
        wireWidgets();
        createSetNumbers();
    }

    //creates the numbers on the buttons
    //then sets the text on the buttons to the numbers
    private void createSetNumbers()
    {
        int range = MAX_NUM - MIN_NUM + 1;
        num1 = (int) (Math.random() * range) + MIN_NUM;
        num2 = (int) (Math.random() * range) + MIN_NUM;
        button1.setText(String.valueOf(num1));
        button2.setText(String.valueOf(num2));
    }

    //links the widgets with a separate method
    private void wireWidgets()
    {
        textViewScore = findViewById(R.id.textview_main_score);
        constraintLayoutMain = findViewById(R.id.constraintlayout_main);
        button1 = findViewById(R.id.button_main_number1);
        button2 = findViewById(R.id.button_main_number2);
        score = 0;
    }

    public void checkAnswer(boolean leftPressed)
    {
        if((num1 > num2 && leftPressed) || num2 > num1 && !leftPressed)
        {
            score++;
            //set the background color
            constraintLayoutMain.setBackgroundColor(Color.rgb(0,255,0));
            //makes a toast based on the conditions of the button numbers
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            score--;
            //set the background color
            constraintLayoutMain.setBackgroundColor(Color.rgb(255,0,0));
            //makes a toast based on the conditions of the button numbers
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        //sets the text in the score textview
        String scoreString = getResources().getString(R.string.main_score);
        textViewScore.setText(scoreString + score);

        //creates new num1 and num2 in the method createSetNumbers()
        createSetNumbers();
    }

    //the activity of the left button
    public void onLeftClick(View view)
    {
        checkAnswer(true);
    }

    //the activity of the right button
    public void onRightClick(View view)
    {
        checkAnswer(false);
    }
}
