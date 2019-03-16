package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Objects.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    private EditText firstNameEdt;
    private EditText lastNameEdt;
    private EditText usernameEdt;
    private EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameEdt = findViewById(R.id.firstName);
        lastNameEdt =  findViewById(R.id.lastName);
        usernameEdt =  findViewById(R.id.username);
        passwordEdt =  findViewById(R.id.password);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference usersRef = database.getReference("users");

        findViewById(R.id.usernameSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEdt.getText().toString();
                lastName = lastNameEdt.getText().toString();
                username = usernameEdt.getText().toString();
                password = passwordEdt.getText().toString();

                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(username).exists()) {
                            Toast.makeText(SignUp.this, "username is already taken, " +
                                    "please pick another",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            User user = new User(username, firstName, lastName, password);
                            usersRef.child(username).setValue(user);
                            Toast.makeText(SignUp.this, "Sign up success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, Home.class);
                            intent.putExtra("user", user);
                            Common.currentUser = user;
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
