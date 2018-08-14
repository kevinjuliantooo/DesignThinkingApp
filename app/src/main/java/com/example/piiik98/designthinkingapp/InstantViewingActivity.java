package com.example.piiik98.designthinkingapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class InstantViewingActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView textView;
    private String dateChoosed = "";
    private int year;
    private int month;
    private int day;

    //Step 1: Create list here...

    /**
     * Must Read For Spinner
     *
     * Step 1: Create the list containing all time (The interval is every 15 minutes, e.g. 7.00 AM, 7.15 AM, etc)
     *
     * Step 2: Copy all spinner code to Spinner Action
     *
     * Step 3: Connect the list into the code (This is quite complicated so you can ask me from Facebook, LOL... 😂😂😂)
     *
     * Step 4: For Testing, you can try to print after the code. You will get the result in command line
     *
     * This step is not clear enough. So if there any difficulty you can ask me.
     *
     *                          Good Luck Have Fun...
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_viewing);

        calendarView = findViewById(R.id.calendarView);
        textView = findViewById(R.id.textView);

        currentDate();          //Set Date of Today
        UIUpdated();            //Update UI

        //Calender Action - Kevin's Part
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int cYear, int cMonth, int cDay) {
                year = cYear;
                month = cMonth;
                day = cDay;
                System.out.println(dateChoosed);
                UIUpdated();

            }
        });


        //Spinner Action - Rita's Part
        
        //Activity_main xml
        
        <?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Spinner
        android:id="@+id/spinner1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />


//strings.xml
            <resources>
    <string name="app_name">My Application</string>
    
    <string-array name="numbers">
        <item>One</item>
        <item>Two</item>
        <item>Three</item>
        <item>Four</item>
        <item>Five</item>
    </string-array>

</resources>

        
//mainActivity.java
            
        ation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
        //Step 2 - 4: Add and Modify Code + Get Result



    }

    // 👇👇👇👇👇👇👇👇 ONLY FOR METHOD BELOW 👇👇👇👇👇👇👇👇 //

    private void currentDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateChoosed = year + ", " + month + ", "+ day;
    }

    private void UIUpdated() {

        //Update Date
        textView.setText(toString().valueOf("Year: " + year + "Month: " + month + "Day: " + day));
        dateChoosed = year + ", " + month + ", "+ day;

        //Update Spinner

    }
}
