package com.example.piiik98.designthinkingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UploadActivity extends AppCompatActivity {

    private Button imagePickerButton, confirmButton;
    private ImageView choosenImage;
    private EditText nameText, locationText, bedroomText, bathroomText, priceText;
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference mDatabase;
    private StorageReference storageReference;
    private String UID;
    private Uri imageUri;
    private InputStream imageStream;
    private int n;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imagePickerButton = findViewById(R.id.imagePickerButton);
        confirmButton =  findViewById(R.id.confirmButton);
        choosenImage = findViewById(R.id.choosenImage);

        nameText = findViewById(R.id.nameText);
        locationText = findViewById(R.id.locationText);
        bedroomText = findViewById(R.id.bedroomText);
        bathroomText = findViewById(R.id.bathroomText);
        priceText = findViewById(R.id.priceText);



        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        UID = firebaseUser.getUid();




        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Random rand = new Random();
                StorageReference houseImageRef = FirebaseStorage.getInstance().getReference().child("houseImage/" + UID + nameText.getText().toString() + ".png");
                mDatabase = FirebaseDatabase.getInstance().getReference().child("upload");

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        n = (int) (dataSnapshot.getChildrenCount() + 1);
                        System.out.println(n);
//                        for (DataSnapshot snap: dataSnapshot.getChildren()) {
//                            Log.e(snap.getKey(),snap.getChildrenCount() + "");
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                Random random = new Random();
                final String today = dateFormat.format(date);

                Map data = new HashMap();
                data.put("name", nameText.getText().toString());
                data.put("location", locationText.getText().toString());
                data.put("pnumber", bedroomText.getText().toString());
                data.put("bathroom", bathroomText.getText().toString());
                data.put("price", priceText.getText().toString());
                data.put("date", today);

                mDatabase.child(UID + String.valueOf(random.nextInt(1000))).setValue(data);

                houseImageRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Upload upload = new Upload("imageFrom" + UID + today, taskSnapshot.getMetadata().toString());
                        DatabaseReference uploadID = mDatabase.push().child("image");
                        mDatabase.child(String.valueOf(uploadID)).setValue(upload);
                    }
                });

                Intent intent = new Intent(UploadActivity.this, MainActivity.class);
                startActivity(intent);
//                UploadTask uploadImage = houseImageRef.putStream(imageStream);
//                uploadImage.addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        taskSnapshot.getMetadata();
//                    }
//                });
            }
        });

        nameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    contentValidation();
                }
            }
        });

        locationText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    contentValidation();
                }
            }
        });

        bedroomText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    contentValidation();
                }
            }
        });

        bathroomText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    contentValidation();
                }
            }
        });

        priceText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    contentValidation();
                }
            }
        });

        imagePickerButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                contentValidation();
            }
        });

        imagePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                contentValidation();
            }
        });

        contentValidation();

    }


    private void contentValidation() {
        if (nameText.getText().toString().isEmpty() ||
                locationText.getText().toString().isEmpty() ||
                bedroomText.getText().toString().isEmpty() ||
                bathroomText.getText().toString().isEmpty() ||
                priceText.getText().toString().isEmpty() ||
                choosenImage.getDrawable() == null) {
            confirmButton.setVisibility(View.INVISIBLE);
        } else {
            confirmButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                //choosenImage.setImageBitmap(BitmapFactory.decodeStream(imageStream));
                choosenImage.setImageURI(imageUri);
            } catch (FileNotFoundException e) {
                // Handle the error
            } finally {
                if (imageStream != null) {
                    try {
                        imageStream.close();
                    } catch (IOException e) {
                        // Ignore the exception
                    }
                }
            }

        }
    }

}
