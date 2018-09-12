package com.example.piiik98.designthinkingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {

    private Button imagePickerButton, confirmButton;
    private ImageView choosenImage;
    private EditText nameText, locationText, bedroomText, bathroomText, priceText;
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
            Uri imageUri = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                choosenImage.setImageBitmap(BitmapFactory.decodeStream(imageStream));
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
