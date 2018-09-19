package com.example.piiik98.designthinkingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookActivity extends AppCompatActivity {

    private TextView bedroom, bathroom, price;
    private String choosenRowKey;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private Button book;
    private ImageView star;
    private Boolean favorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bedroom = findViewById(R.id.Bedroom);
        bathroom = findViewById(R.id.Bathroom);
        price = findViewById(R.id.Price);
        book = findViewById(R.id.book);
        star = findViewById(R.id.star);

        choosenRowKey = getIntent().getExtras().getString("choosenRow");
        System.out.println(choosenRowKey);

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference();

        ref.child("upload").child(choosenRowKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bedroom.setText("Bedroom: " + String.valueOf(dataSnapshot.child("bedroom").getValue()));
                bathroom.setText("Bathroom" + String.valueOf(dataSnapshot.child("bathroom").getValue()));
                price.setText("Price: $"+ String.valueOf(dataSnapshot.child("price").getValue()));
                //system.out.println(ds);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favorite == false) {
                    star.setImageResource(R.drawable.blank);
                    favorite = true;
                } else {
                    star.setImageResource(R.drawable.full);
                    favorite = false;
                }

            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookActivity.this, InstantViewingActivity.class);
                intent.putExtra("owner", choosenRowKey);
                startActivity(intent);
            }
        });
    }
}
