package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private TextView textViewQuestions;
    private Button buttonTrue;
    private Button buttonFalse;
    private int currentQuestion;
    public static final String EXTRA_SCORE = "score";


    private int score;
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream questionsInputStream = getResources().openRawResource(R.raw.questions);
        String jsonString = readTextFile(questionsInputStream);
        // create a gson object
        Gson gson = new Gson();
        // read your json file into an array of questions
        Question[] questions =  gson.fromJson(jsonString, Question[].class);
        // convert your array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        // verify that it read everything properly
        Log.d(TAG, "onCreate: " + questionList.toString());

        quiz = new Quiz(questionList);
        currentQuestion = 0;
        score = 0;
        wireWidgets();
        setListeners();
    }

    private void wireWidgets() {
        textViewQuestions = findViewById(R.id.textView_main_question);
        buttonFalse = findViewById(R.id.button_main_false);
        buttonTrue = findViewById(R.id.button_main_true);

    }


    private void setListeners() {
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quiz.checkAnswer(true, currentQuestion)){
                    score++;
                    Toast.makeText(MainActivity.this, "\uD83D\uDE00", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "\uD83D\uDE43", Toast.LENGTH_SHORT).show();
                currentQuestion++;
                if(currentQuestion == 10){
                    //go to end screen
                    Intent scoreIntent = new Intent(MainActivity.this, EndActivity.class);
                    scoreIntent.putExtra(EXTRA_SCORE, score);
                    startActivity(scoreIntent);
                    score = 0;
                    currentQuestion = 0;
                    textViewQuestions.setText(String.valueOf(quiz.getQuestion(currentQuestion)));
                }
                textViewQuestions.setText(String.valueOf(quiz.getQuestion(currentQuestion)));


            }
        });
        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quiz.checkAnswer(false, currentQuestion)){
                    score++;
                    Toast.makeText(MainActivity.this, "\uD83D\uDE00", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "\uD83D\uDE43", Toast.LENGTH_SHORT).show();
                currentQuestion++;
                if(currentQuestion == 10){
                    //go to end screen
                    Intent scoreIntent = new Intent(MainActivity.this, EndActivity.class);
                    scoreIntent.putExtra(EXTRA_SCORE, score);
                    startActivity(scoreIntent);
                    score = 0;
                    currentQuestion = 0;
                    textViewQuestions.setText(String.valueOf(quiz.getQuestion(currentQuestion)));
                }
                textViewQuestions.setText(String.valueOf(quiz.getQuestion(currentQuestion)));


            }
        });
    }
    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
