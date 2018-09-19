package com.example.piiik98.designthinkingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText name, email, pnumber, pass1, pass2;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        pnumber = (EditText)findViewById(R.id.Pnumber);
        pass1 = (EditText)findViewById(R.id.password1);
        pass2 = (EditText)findViewById(R.id.password2);


    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void updateUI(FirebaseUser currentUser) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(currentUser.getUid());

        Map data = new HashMap();
        data.put("name", name.getText().toString());
        data.put("email", email.getText().toString());
        data.put("pnumber", pnumber.getText().toString());
        data.put("pass", pass1.getText().toString());

        mDatabase.setValue(data);
    }

    public void onsignupClick (View v){
        if (v.getId()== R.id.signupbutton) {

            if (!pass1.getText().toString().equals(pass2.getText().toString())){
                //popup message
                Toast pass = Toast.makeText(SignupActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
            } else {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass1.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("signup", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("signup", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "The password must be 8 character or more",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }


                                // ...
                            }
                        });
            }
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);

        }
    }

}
