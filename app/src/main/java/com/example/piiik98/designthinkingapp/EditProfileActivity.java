package com.example.piiik98.designthinkingapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView name, email, pnumber, password1, nationality, from, flanguage, slanguage;
    private Button OKButton;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private String UID;
    private Uri imageUri;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pnumber = findViewById(R.id.Pnumber);
        password1 = findViewById(R.id.password1);
        nationality = findViewById(R.id.nationality);
        from = findViewById(R.id.from);
        flanguage = findViewById(R.id.flanguage);
        slanguage = findViewById(R.id.slanguage);
        OKButton = findViewById(R.id.OKButton);
        profileImage = findViewById(R.id.profileImage);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference ref = firebaseDatabase.getReference().child("user");
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        UID = firebaseUser.getUid();

        ref.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText((String) dataSnapshot.child("name").getValue());
                email.setText((String) dataSnapshot.child("email").getValue());
                pnumber.setText((String) dataSnapshot.child("pnumber").getValue());
                password1.setText((String) dataSnapshot.child("password").getValue());
                nationality.setText((String) dataSnapshot.child("nationality").getValue());
                from.setText((String) dataSnapshot.child("from").getValue());
                flanguage.setText((String) dataSnapshot.child("flanguage").getValue());
                slanguage.setText((String) dataSnapshot.child("slanguage").getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() ||
                        email.getText().toString().isEmpty() ||
                        pnumber.getText().toString().isEmpty() ||
                        password1.getText().toString().isEmpty() ||
                        nationality.getText().toString().isEmpty() ||
                        flanguage.getText().toString().isEmpty() ||
                        slanguage.getText().toString().isEmpty()) {
                        Toast.makeText(EditProfileActivity.this, "Please Fill everyting not including from", Toast.LENGTH_LONG).show();

                } else {
                    final DatabaseReference mDatabase = ref.child(UID);

                    Map data = new HashMap();
                    data.put("name", name.getText().toString());
                    data.put("email", email.getText().toString());
                    data.put("pnumber", pnumber.getText().toString());
                    data.put("password", password1.getText().toString());
                    data.put("nationality", nationality.getText().toString());
                    data.put("from", from.getText().toString());
                    data.put("flanguage", flanguage.getText().toString());
                    data.put("slanguage", slanguage.getText().toString());

                    mDatabase.setValue(data);
                    StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("userProfile/" + UID + ".png");

                    imageRef.putFile(imageUri).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Upload upload = new Upload("imageFrom " + UID, taskSnapshot.getMetadata().toString());
                            String uploadID = mDatabase.push().getKey();
                            mDatabase.child(uploadID).setValue(upload);
                        }
                    });

                    Intent intent = new Intent(EditProfileActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                //choosenImage.setImageBitmap(BitmapFactory.decodeStream(imageStream));
                profileImage.setImageURI(imageUri);
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
