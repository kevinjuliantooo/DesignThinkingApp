package com.example.piiik98.designthinkingapp;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 5000;
    private ViewPager viewPager;

    private ListView houseList;
    private int houseImage[] = {};
    private int bed[] = {};
    private int bathroom[] = {};
    private int price[] = {};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        houseList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), houseImage, bed, bathroom, price);
        houseList.setAdapter(customAdapter);

        viewPager = findViewById(R.id.viewpager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        changeSlideImageByTime();
        
    }

    private void changeSlideImageByTime() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 3) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }


}
