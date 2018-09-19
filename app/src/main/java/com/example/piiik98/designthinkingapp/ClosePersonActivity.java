package com.example.piiik98.designthinkingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ClosePersonActivity extends AppCompatActivity {

    private ListView friendList;
    private ToggleButton selectNationality;
    private Spinner selectLocation;

    private ArrayList<String> nameArray = new ArrayList<String>();
    private ArrayList<String> locationArray = new ArrayList<String>();
    private ArrayList<String> nationalityArray = new ArrayList<String>();
    private ArrayList<String> firstLanguageArray = new ArrayList<String>();
    private ArrayList<String> secondaryLanguageArray = new ArrayList<String>();
    private ArrayList<Integer> profileImageArray = new ArrayList<Integer>();

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference ref;

    private String choosedLocation = "Default";
    private Boolean choosedNationality = false;
    private String userNationality = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_person);
        final String[] select = {"Default", "Indonesia", "Malaysia", "Singapore"};

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        selectLocation = findViewById(R.id.location);
        selectNationality = findViewById(R.id.nationality);

        selectLocation.setVisibility(View.VISIBLE);       // Have no way to solve
        selectNationality.setVisibility(View.VISIBLE);    // Have no way to solve

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, select);
        selectLocation.setAdapter(spinnerAdapter);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference().child("user");

        ref.child(firebaseUser.getUid()).child("nationality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userNationality = (String) dataSnapshot.getValue();
                System.out.println(userNationality);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        selectLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        choosedLocation = select[0];
                        break;
                    case 1:
                        choosedLocation = select[1];
                        break;
                    case 2:
                        choosedLocation = select[2];
                        break;
                    case 3:
                        choosedLocation = select[3];
                        break;
                }
                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        selectNationality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    choosedNationality = true;
                } else {
                    choosedNationality = false;
                }
                updateUI();
            }
        });

        setSupportActionBar(toolbar);

        friendList = findViewById(R.id.friendList);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void rowSort(DataSnapshot ds) {
        profileImageArray.add(null);
        nameArray.add("Name: " +(String) ds.child("name").getValue());
        locationArray.add("From: " +(String) ds.child("from").getValue());
        nationalityArray.add("Nationality: " + (String) ds.child("nationality").getValue());
        firstLanguageArray.add("First Language: " + (String) ds.child("fLanguage").getValue());
        secondaryLanguageArray.add("Second Language: " + (String) ds.child("sLanguage").getValue());
        System.out.println(ds.child("name").getValue());
    }

    private void updateUI() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CustomAdapterForCloseFriendActivity customAdapterForCloseFriendActivity = new CustomAdapterForCloseFriendActivity(getApplicationContext(), profileImageArray, nameArray, locationArray, nationalityArray, firstLanguageArray, secondaryLanguageArray, choosedLocation, choosedNationality);
                if (customAdapterForCloseFriendActivity.getCount() != 0){
                    profileImageArray.clear();
                    nameArray.clear();
                    locationArray.clear();
                    nationalityArray.clear();
                    firstLanguageArray.clear();
                    secondaryLanguageArray.clear();
                }
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    System.out.println(ds.child("nationality").getValue()); //Contoh Indonesia
                    if (!choosedNationality) { //Kalo Nationality Off
                        if (choosedLocation.equals("Default")) {  // Misal kita from ""
                            rowSort(ds);
                        } else if (choosedLocation.equals(ds.child("location").getValue())) { // Misal lokasi yang kita pilih w/ orang sama
                            rowSort(ds);
                        } else {
                            // Empty
                        }

                    } else { //Kalo nationality on
                        if(userNationality.equals(ds.child("nationality").getValue())){
                            if (choosedLocation.equals("Default")) {
                                rowSort(ds);
                            } else if (choosedLocation.equals(ds.child("location").getValue())) { // Misal lokasi yang kita pilih w/ orang sama
                                rowSort(ds);
                            } else {
                                // Empty
                            }
                        } else {
                            // Empty
                        }
                    }
                }
                friendList.setAdapter(customAdapterForCloseFriendActivity);
                //friendList.setAdapter(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
