package com.task5.stages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Stage2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Stage2_Model> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage_2);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.stage2_recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(Stage2.this));
        dataList = new ArrayList<>();
        Stage2_Model model = new Stage2_Model();

        model.setText("Cats");
        model.setImage(R.drawable.cat_images);
        dataList.add(model);

        Stage2_Model model1 = new Stage2_Model();
        model1.setText("Dogs");
        model1.setImage(R.drawable.dog_image);
        dataList.add(model1);

        Stage2_Model model2 = new Stage2_Model();
        model2.setText("Hamsters");
        model2.setImage(R.drawable.hamster_image);
        dataList.add(model2);

        Stage2_Model model3 = new Stage2_Model();
        model3.setText("Parrot");
        model3.setImage(R.drawable.parrot_image);
        dataList.add(model3);

        Stage2_Model model4 = new Stage2_Model();
        model4.setText("Golden Fish");
        model4.setImage(R.drawable.goldfish_image);
        dataList.add(model4);

        recyclerView.setAdapter(new Stage2_Adapter(Stage2.this,dataList));
    }
}

