package com.example.piiik98.designthinkingapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ClosePersonActivity extends AppCompatActivity {

    private ListView friendList;
    private String[] name = {"Rita"};
    private String[] location = {"Geylang"};
    private String[] nationality = {"China"};
    private String[] firstLanguage = {"Chinnese"};
    private String[] secondaryLanguage = {"English"};
    private int[] profileImage = {R.drawable.house1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        friendList = findViewById(R.id.friendList);
        CustomAdapterForCloseFriendActivity customAdapterForCloseFriendActivity = new CustomAdapterForCloseFriendActivity(getApplicationContext(), profileImage, name, location, nationality, firstLanguage, secondaryLanguage);
        friendList.setAdapter(customAdapterForCloseFriendActivity);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
