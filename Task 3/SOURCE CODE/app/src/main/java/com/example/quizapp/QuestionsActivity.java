package com.example.quizapp;

import android.animation.Animator;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity{
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference score_push = database.getReference().child("score1");
    private int count = 0;
    private LinearLayout options_container;
    private Button next;
    private Button option4;
    private Button option3;
    private Button option2;
    private Button option1;
    private TextView question_no;
    private TextView question;
    private EditText time;
    private List<QuestionsModel> list;
    private int score = 0, tcount = 9;
    private int position;
    private int activityReferences = 0;
    private boolean isActivityChangingConfigurations = false;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_layout);

        getSupportActionBar().hide();

        time = (EditText) findViewById(R.id.text_time);
        new CountDownTimer(600000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished / 60000 < 10 && (millisUntilFinished / 1000) % 60 < 10) {
                    time.setText("0" + millisUntilFinished / 60000 + ":" + "0" + (millisUntilFinished / 1000) % 60);
                } else if (millisUntilFinished / 60000 >= 10 && (millisUntilFinished / 1000) % 60 < 10) {
                    time.setText(millisUntilFinished / 60000 + ":" + "0" + (millisUntilFinished / 1000) % 60);
                } else if (millisUntilFinished / 60000 < 10 && (millisUntilFinished / 1000) % 60 >= 10) {
                    time.setText("0" + millisUntilFinished / 60000 + ":" + (millisUntilFinished / 1000) % 60);
                } else {
                    time.setText(millisUntilFinished / 60000 + ":" + (millisUntilFinished / 1000) % 60);
                }

            }

            public void onFinish() {
                End(score);
            }
        }.start();
        question = (TextView) findViewById(R.id.textView_question);
        question_no = (TextView) findViewById(R.id.textView_question_no);
        option1 = (Button) findViewById(R.id.button_option1);
        option2 = (Button) findViewById(R.id.button_option2);
        option3 = (Button) findViewById(R.id.button_option3);
        option4 = (Button) findViewById(R.id.button_option4);
        next = (Button) findViewById(R.id.button_next);
        options_container = (LinearLayout) findViewById(R.id.options_container);
        list = new ArrayList<>();
        myRef.child("Questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    if (snapshot.exists()) {
                        list.add(snapshot.getValue(QuestionsModel.class));
                    }
                }
                if (list.size() > 0) {
                    for (int i = 0; i < 4; i++) {
                        options_container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View v) {
                                checkAnswer((Button) v);
                            }
                        });
                    }
                    playAnim(question, 0, list.get(position).getQuestion());
                    next.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {
                            next.setEnabled(false);
                            next.setAlpha(0.7f);
                            position++;
                            enableOption(true);
                            if (position == list.size()) {
                                End(score);
                            }
                            count = 0;
                            playAnim(question, 0, list.get(position).getQuestion());
                        }
                    });
                } else {
                    finish();
                    Toast.makeText(QuestionsActivity.this, "No Questions available", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuestionsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    private void playAnim(View view, int value, String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getOptionA();
                    } else if (count == 1) {
                        option = list.get(position).getOptionB();
                    } else if (count == 2) {
                        option = list.get(position).getOptionC();
                    } else if (count == 3) {
                        option = list.get(position).getOptionD();
                    }
                    playAnim(options_container.getChildAt(count), 0, option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        question_no.setText(Integer.toString((position + 1)) + "/" + Integer.toString(list.size()));

                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void End(int score) {
        SharedPreferences sp = getSharedPreferences("score_file", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        ed.putInt("score", score);
        ed.commit();

        HashMap hashMap = new HashMap();
        hashMap.put("score", score);
        score_push.updateChildren(hashMap);
        Intent scoreIntent = new Intent(QuestionsActivity.this, ScoreActivity.class);
        //scoreIntent.putExtra("score", score);
        startActivity(scoreIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption) {
        enableOption(false);
        next.setEnabled(true);
        next.setAlpha(1);
        if (selectedOption.getText().toString().equals(list.get(position).getCorrectAns())) {
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#66CD00")));
            score++;
        } else {
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            Button Correctoption = (Button) options_container.findViewWithTag(list.get(position).getCorrectAns());
            Correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#66CD00")));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            options_container.getChildAt(i).setEnabled(enable);
            if (enable) {
                options_container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        End(score);
    }
}

