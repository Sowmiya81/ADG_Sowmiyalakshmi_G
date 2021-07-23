package com.example.quizapp;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private int id1;
    private static final String TAG = "Swipe Position";
    private float x1,x2,y1,y2;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_bar);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().hide();

        //initialize gesturedetector
        this.gestureDetector = new GestureDetector(BottomNavActivity.this,this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainActivity()).commit();
    }

    //Override onTouch event


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        switch (event.getAction()){
            //starting to swipe time
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

            case  MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                //getting value for horizontal swipe
                float valueX = x2-x1;

                //getting value for vertical swipe
                float valueY = y2-y1;

                if(Math.abs(valueX)>MIN_DISTANCE)
                {
                    Fragment selected_fragment;
                    //detect left to right swipe
                    if(x2>x1)
                    {

                        if(id1 == R.id.nav_profile)
                        {
                            selected_fragment = new MainActivity();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selected_fragment).commit();
                        }
                    }
                    else {
                            //detect right to left swipe
                        if(id1 == R.id.nav_home)
                        {
                            selected_fragment = new ProfileFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    selected_fragment).commit();
                        }

                        }
                }

        }
        return super.onTouchEvent(event);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    id1 = item.getItemId();
                    switch(id1){
                        case R.id.nav_home:
                            selectedFragment = new MainActivity();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
