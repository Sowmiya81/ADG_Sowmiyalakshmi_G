package com.task5.stages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button stage1,stage2,stage3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        stage1 = findViewById(R.id.button_1);
        stage2 = findViewById(R.id.button_2);
        stage3 = findViewById(R.id.button_3);

        stage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Stage1.class);
                startActivity(intent);
            }
        });

        stage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Stage2.class);
                startActivity(intent);
            }
        });

        stage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Stage3.class);
                startActivity(intent);
            }
        });
    }
}