package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    private int score;
    private Button buttonPlayAgain;
    private TextView textViewScore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        wireWidgets();
        String scoreMessage1 = getString(R.string.scoreMessage1);
        String scoreMessage2 = getString(R.string.scoreMessage2);
        Intent lastIntent = getIntent();
        score = lastIntent.getIntExtra(MainActivity.EXTRA_SCORE, -1);
        textViewScore.setText(scoreMessage1 + score + scoreMessage2);
        setListoners();
    }

    private void wireWidgets() {

        buttonPlayAgain = findViewById(R.id.button_end_restart);
        textViewScore = findViewById(R.id.textView_end_score);
    }

    private void setListoners() {
        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }
}
