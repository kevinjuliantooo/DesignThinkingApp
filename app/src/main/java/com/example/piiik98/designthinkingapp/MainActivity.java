package com.example.piiik98.designthinkingapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 5000;
    private ViewPager viewPager;
    private TextView button3;
    private Spinner spinner;
    private ListView houseList;
    private int nUpload = 0;
    private DatabaseReference ref, houseListRef;

    private ArrayList<Integer> houseImage = new ArrayList<>();
    private ArrayList<String> bed = new ArrayList<>();
    private ArrayList<String> bathroom = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] sortBy = {"Sort by Date", "Sort by Popularity"};

        houseList = (ListView) findViewById(R.id.houseList);
        button3 = findViewById(R.id.textView7); //Fix it later
        spinner = findViewById(R.id.spinner);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        nUpload = 1;
        String UID = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("upload");

        FirebaseStorage storage = FirebaseStorage.getInstance();
        //StorageReference houseImageRef = storage.getReference().child("upload").child(UID);

        //CustomAdapterForMainActivity customAdapter = new CustomAdapterForMainActivity(getApplicationContext(), houseImage, bed, bathroom, price);
        //houseList.setAdapter(customAdapter);

        viewPager = findViewById(R.id.viewpager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        changeSlideImageByTime();
        getAllHouseList();

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sortBy);
        spinner.setAdapter(spinnerAdapter);

        houseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int n = 0;
                        for (DataSnapshot ds: dataSnapshot.getChildren()) {
                            if (n == i){
                                n = 0;
                                String personID = ds.getKey();
                                System.out.println("to: " + personID);
                                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                                intent.putExtra("choosenRow", personID);
                                startActivity(intent);
                                break;
                            }
                            n += 1;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    private void getAllHouseList() {

//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                HouseData houseData = dataSnapshot.getValue(HouseData.class);
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    Log.v("tmz",""+ ds.child("name").getValue());
//                    Log.v("tmz",""+ ds.child("location").getValue());
//                    Log.v("tmz",""+ ds.child("bedroom").getValue());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CustomAdapterForMainActivity customAdapter = new CustomAdapterForMainActivity(getApplicationContext(),houseImage, bed, bathroom, price);
// info@comfortdelgro.com
                if (customAdapter.getCount() != 0){
                    houseImage.clear();
                    bed.clear();
                    bathroom.clear();
                    price.clear();
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                  HouseData houseData = dataSnapshot.getValue(HouseData.class);
                    houseImage.add(R.drawable.house1);
                    bed.add((String) ds.child("bedroom").getValue());
                    bathroom.add((String) ds.child("bathroom").getValue());
                    price.add((String) ds.child("price").getValue());
                }
                houseList.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
