package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

TextView textView;
Button button;
static int correctAnswer;
String correctAnswerToString;
static int correctAnswersCount;
static int wrongAnswersCount;
String textInGoodWrongWindow;
TextView timerTextView;

public void hideElements(){
    textView = findViewById(R.id.timeTextView);
    textView.setVisibility(View.INVISIBLE);
    textView = findViewById(R.id.goodWrongTextView2);
    textView.setVisibility(View.INVISIBLE);
    textView = findViewById(R.id.questionTextView3);
    textView.setVisibility(View.INVISIBLE);
    textView = findViewById(R.id.answerATextView);
    textView.setVisibility(View.INVISIBLE);
    textView = findViewById(R.id.answerBTextView);
    textView.setVisibility(View.INVISIBLE);
    textView = findViewById(R.id.answerCTextView);
    textView.setVisibility(View.INVISIBLE);
    textView = findViewById(R.id.answerDTextView);
    textView.setVisibility(View.INVISIBLE);
}
public void showElements(View view){
    textView = findViewById(R.id.textView9);
    textView.setClickable(false);
    textView.setVisibility(View.INVISIBLE);
    textView = findViewById(R.id.timeTextView);
    textView.setVisibility(View.VISIBLE);
    textView = findViewById(R.id.goodWrongTextView2);
    textView.setVisibility(View.VISIBLE);
    textView = findViewById(R.id.questionTextView3);
    textView.setVisibility(View.VISIBLE);
    textView = findViewById(R.id.answerATextView);
    textView.setVisibility(View.VISIBLE);
    textView = findViewById(R.id.answerBTextView);
    textView.setVisibility(View.VISIBLE);
    textView = findViewById(R.id.answerCTextView);
    textView.setVisibility(View.VISIBLE);
    textView = findViewById(R.id.answerDTextView);
    textView.setVisibility(View.VISIBLE);
    setQuestion();
    timer();
}
//public void showGoButton(View view){}
//public void hideGoButton(View view){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        hideElements();
        textView = findViewById(R.id.goodWrongTextView2);
        textView.setText("0/0");
    }

    public int getRandomNumber(int bound){
        Random r = new Random();
        return r.nextInt(bound);
    }
    public void setQuestion(){
        int firstNumber = getRandomNumber(20);
        int secondNumber = getRandomNumber(20);
        correctAnswer = firstNumber+secondNumber;
        correctAnswerToString = Integer.toString(correctAnswer);
        textView = findViewById(R.id.questionTextView3);
        String add = firstNumber+ " + " +secondNumber;
        textView.setText(add);

        int tmp = getRandomNumber(40);
        textView = findViewById(R.id.answerATextView);
        textView.setText(Integer.toString(tmp));
        textView.setTag(" ");

        tmp = getRandomNumber(40);
        textView = findViewById(R.id.answerBTextView);
        textView.setText(Integer.toString(tmp));
        textView.setTag(" ");

        tmp = getRandomNumber(40);
        textView = findViewById(R.id.answerCTextView);
        textView.setText(Integer.toString(tmp));
        textView.setTag(" ");

        tmp = getRandomNumber(40);
        textView = findViewById(R.id.answerDTextView);
        textView.setText(Integer.toString(tmp));
        textView.setTag(" ");

        int whatAnswer = getRandomNumber(4);
        if(whatAnswer == 0) {
            textView = findViewById(R.id.answerATextView);
            textView.setText(correctAnswerToString);
            textView.setTag("correct");
        }
        if(whatAnswer == 1) {
            textView = findViewById(R.id.answerBTextView);
            textView.setText(correctAnswerToString);
            textView.setTag("correct");
        }
        if(whatAnswer == 2) {
            textView = findViewById(R.id.answerCTextView);
            textView.setText(correctAnswerToString);
            textView.setTag("correct");
        }
        if(whatAnswer == 3) {
            textView = findViewById(R.id.answerDTextView);
            textView.setText(correctAnswerToString);
            textView.setTag("correct");
        }
    }
    public void correctWrong(boolean correct, boolean wrong, boolean clear){
    textView = findViewById(R.id.goodWrongTextView2);
    if(correct)
        correctAnswersCount++;
    else if(wrong)
        wrongAnswersCount++;
    else if (clear)
        {
            correctAnswersCount =0;
            wrongAnswersCount = 0;
        }
    textInGoodWrongWindow = Integer.toString(correctAnswersCount) + " / " + Integer.toString(wrongAnswersCount);
    textView.setText(textInGoodWrongWindow);
    }
    public void checkAnswer(View view){

    if(view.getTag().equals("correct")) {
        correctWrong(true, false, false);
        textView = findViewById(R.id.wrongCorrectTextView2);
        textView.setText("BRAWO !!");
    }
    else {
        correctWrong(false, true, false);
        textView = findViewById(R.id.wrongCorrectTextView2);
        textView.setText("ŹLE :(");
    }
    setQuestion();
    }
    public void timer(){
        CountDownTimer timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView = findViewById(R.id.timeTextView);
                String timeToFinish= Integer.toString((int)millisUntilFinished/1000) + "s";
                Log.i("czas",timeToFinish);
                timerTextView.setText(timeToFinish);
            }

            @Override
            public void onFinish() {
                textView = findViewById(R.id.wrongCorrectTextView2);
                textView.setText("Koniec czasu");
                button = findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);

                textView = findViewById(R.id.answerATextView);
                textView.setClickable(false);
                textView = findViewById(R.id.answerBTextView);
                textView.setClickable(false);
                textView = findViewById(R.id.answerCTextView);
                textView.setClickable(false);
                textView = findViewById(R.id.answerDTextView);
                textView.setClickable(false);
            }
        }.start();
    }
    public void playAgain(View view){
        correctWrong(false,false,true);
        setQuestion();
        timer();
        button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.wrongCorrectTextView2);
        textView.setText(" ");
        textView = findViewById(R.id.answerATextView);
        textView.setClickable(true);
        textView = findViewById(R.id.answerBTextView);
        textView.setClickable(true);
        textView = findViewById(R.id.answerCTextView);
        textView.setClickable(true);
        textView = findViewById(R.id.answerDTextView);
        textView.setClickable(true);
    }
}
