package com.task5.stages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Stage3 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Stage3_Model> dataList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stage_3);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.stage3_recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(Stage3.this));
        dataList = new ArrayList<>();

        Stage3_Model model1 = new Stage3_Model();
        model1.setTitle("Jack Daniel");
        model1.setDesc("Director, Cinematographer");
        model1.setFollowers("12.6k");
        model1.setPosts("330");
        model1.setFollowing("1211");
        model1.setImage(R.drawable.image1);
        dataList.add(model1);

        Stage3_Model model2 = new Stage3_Model();
        model2.setTitle("John Walker");
        model2.setDesc("Photographer, Artist");
        model2.setFollowers("128.6k");
        model2.setPosts("150");
        model2.setFollowing("90");
        model2.setImage(R.drawable.image2);
        dataList.add(model2);

        recyclerView.setAdapter(new Stage3_Adapter(Stage3.this,dataList));
    }
}
