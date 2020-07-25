package com.example.teamwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_in extends AppCompatActivity {

    EditText Email,Password;
    Button Signin;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mauth=FirebaseAuth.getInstance();
        Email=(EditText)findViewById(R.id.SEmail);
        Password=(EditText)findViewById(R.id.Spassword);
        Signin=(Button)findViewById(R.id.Sign);
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=Email.getText().toString();
                password=Password.getText().toString();
                mauth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(sign_in.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(sign_in.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(sign_in.this,Home_Activity.class);
                                   startActivity(intent);
                                }
                                else {
                                    //String user=mauth.getCurrentUser().getEmail();
                                    Toast.makeText(sign_in.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                    //String user=mauth.getCurrentUser().getEmail();
                                }
                            }
                        });
            }
        });
    }
}