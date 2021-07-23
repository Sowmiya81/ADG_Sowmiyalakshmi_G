package com.task5.stages;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Stage1 extends AppCompatActivity {
    private View imp1,imp2,imp3,file1,file2,file3,file4,others;
    private ImageView imp1_img,imp2_img,imp3_img,file1_img,file2_img,file3_img,file4_img,others_img;
    private TextView imp1_text,imp2_text,imp3_text,file1_text,file2_text,file3_text,file4_text,others_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage_1);
        getSupportActionBar().hide();
        imp1 = findViewById(R.id.imp1);
        imp2 = findViewById(R.id.imp2);
        imp3 = findViewById(R.id.imp3);
        file1 = findViewById(R.id.file1);
        file2 = findViewById(R.id.file2);
        file3 = findViewById(R.id.file3);
        file4 = findViewById(R.id.file4);
        others = findViewById(R.id.other1);

        imp1_img = imp1.findViewById(R.id.picture);
        imp1_img.setImageResource(R.drawable.degree_icon);
        imp1_text = imp1.findViewById(R.id.text);
        imp1_text.setText("Degree");

        imp2_img = imp2.findViewById(R.id.picture);
        imp2_img.setImageResource(R.drawable.assignment_icon);
        imp2_text = imp2.findViewById(R.id.text);
        imp2_text.setText("Assignments");

        imp3_img = imp3.findViewById(R.id.picture);
        imp3_img.setImageResource(R.drawable.photos_icon);
        imp3_text = imp3.findViewById(R.id.text);
        imp3_text.setText("Photos");

        file1_img = file1.findViewById(R.id.picture);
        file1_img.setImageResource(R.drawable.png_icon);
        file1_text = file1.findViewById(R.id.text);
        file1_text.setText("Png Images");

        file2_img = file2.findViewById(R.id.picture);
        file2_img.setImageResource(R.drawable.jpg_icon);
        file2_text = file2.findViewById(R.id.text);
        file2_text.setText("Jpg Images");

        file3_img = file3.findViewById(R.id.picture);
        file3_img.setImageResource(R.drawable.doc_icon);
        file3_text = file3.findViewById(R.id.text);
        file3_text.setText("Word Documents");

        file4_img = file4.findViewById(R.id.picture);
        file4_img.setImageResource(R.drawable.pdf_icon);
        file4_text = file4.findViewById(R.id.text);
        file4_text.setText("PDF Documents");

        others_img = others.findViewById(R.id.picture);
        others_img.setImageResource(R.drawable.settings_icon);
        others_text = others.findViewById(R.id.text);
        others_text.setText("Other Settings");
    }
}
