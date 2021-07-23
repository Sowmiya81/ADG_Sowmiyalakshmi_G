package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    private Button btn_logout;
    private TextView textView_score;
    private HashMap<String,Long> hashMap = new HashMap<String,Long>();
    private Long score;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference score_pull = database.getReference().child("score1");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile,container,false);
        textView_score = (TextView)view.findViewById(R.id.textView_score_profile);

        score_pull.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    if (snapshot.exists()) {
                        score=(Long)snapshot.getValue();
                        textView_score.setText(String.valueOf(score)+"/10");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn_logout = (Button)view.findViewById(R.id.button_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences.Editor ed = sp.edit();
                //ed.putBoolean("isSignedIn",false);
                //ed.commit();

                FirebaseAuth.getInstance().signOut();
                Intent rulesIntent = new Intent(getActivity(),LoginOptionsActivity.class);
                startActivity(rulesIntent);
            }
        });
        return view;

    }
}
