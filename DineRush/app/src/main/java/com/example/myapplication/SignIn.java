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

public class SignIn extends AppCompatActivity {
    private String username;
    private String password;
    private EditText usernameEdt;
    private EditText passwordEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameEdt =  (EditText) findViewById(R.id.usernameSignIn);
        passwordEdt =  (EditText) findViewById(R.id.passwordSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference usersRef = database.getReference().child("users");

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = usernameEdt.getText().toString().trim();
                password = passwordEdt.getText().toString().trim();



                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(username).exists()) {
                            User user = dataSnapshot.child(username).getValue(User.class);

                            if(user.getPassword().equals(password)) {
                                Toast.makeText(SignIn.this, "Login success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignIn.this, Home.class);
                                intent.putExtra("user", user);
                                Common.currentUser = user;
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(SignIn.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignIn.this, "username does not exist, " +
                                    "please try again or sign up",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignIn.this, SignUp.class);
                            startActivity(intent);
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
