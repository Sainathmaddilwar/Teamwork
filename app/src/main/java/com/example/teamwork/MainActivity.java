package com.example.teamwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button SignIn;
    Button Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignIn=(Button)findViewById(R.id.SignIn);
        Signup=(Button)findViewById(R.id.Signup);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,sign_in.class);
                startActivity(intent);
            }
        });
    }
}