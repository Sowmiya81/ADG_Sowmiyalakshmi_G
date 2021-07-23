package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {
    private TextView text_score;
    private Button done;
    private int score;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        //int score = intent.getIntExtra("score",0);
        text_score = (TextView) findViewById(R.id.textView_score);
        done = (Button) findViewById(R.id.button_done);
        showScore(text_score);
        //text_score.setText(String.valueOf(score)+"/10");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent done = new Intent(ScoreActivity.this,BottomNavActivity.class);
                startActivity(done);
            }
        });

    }
    public void showScore(View view)
    {
        SharedPreferences sp = getSharedPreferences("score_file",MODE_PRIVATE);
        if(sp.contains("score"))
        {
            score = sp.getInt("score",11);
            text_score.setText(String.valueOf(score)+"/10");
        }
        else {
            text_score.setText("Sorry, there has been an error");
        }
    }
}
