package com.example.piiik98.designthinkingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;

public class InstantViewingActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView textView;
    private String dateChoosed = "";
    private Button done;
    private int year;
    private int month;
    private int day;

    //Step 1: Create list here...


    /**
     * Must Read For Spinner
     *
     * Step 1: Create the list containing all time (The interval is every 15 minutes, e.g. 7.00 AM, 7.15 AM, etc).
     *
     * Step 2: Copy all spinner code to Spinner Action.
     *
     * Step 3: Connect the list into the code (This is quite complicated so you can ask me from Facebook, LOL... 😂😂😂)
     *
     * Step 4: For Testing, you can try to print after the code. You will get the result in command line.
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
        done = findViewById(R.id.done);

        currentDate();          //Set Date of Today
        UIUpdated();            //Update UI

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstantViewingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Calender Action - Kevin's Part
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int cYear, int cMonth, int cDay) {
                year = cYear;
                month = cMonth;
                day = cDay;
                dateChoosed = year + ", " + month + ", "+ day;
                System.out.println(dateChoosed);
                UIUpdated();

            }
        });


        //Spinner Action - Rita's Part

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }


        //Step 2 - 4: Add and Modify Code + Get Result


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



    }
}
