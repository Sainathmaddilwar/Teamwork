package com.example.teamwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText Email,Name,Password,CPassword;
    Button Register;
    TextView echeack,pcheack,ncheack;
    int mail=1,nameb=1,pass=1;
    FirebaseAuth mauth;
   // int red=R.color.red;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mauth=FirebaseAuth.getInstance();
        echeack=(TextView)findViewById(R.id.echeack);
        pcheack=(TextView)findViewById(R.id.pcheack);
        ncheack=(TextView)findViewById(R.id.ncheack);
        Email=(EditText)findViewById(R.id.Email);
        Name=(EditText)findViewById(R.id.Name);
        Password=(EditText)findViewById(R.id.Password);
        CPassword=(EditText)findViewById(R.id.CPassword);
        Register=(Button)findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,name,password,cpassword;
                email=Email.getText().toString();
                name=Name.getText().toString();
                password=Password.getText().toString();
                cpassword=CPassword.getText().toString();
                String p=password;
                //Toast.makeText(SignUp.this,password,Toast.LENGTH_SHORT).show();
                if(isEmailValid(email)!=true){
                    mail=0;
                    echeack.setVisibility(View.VISIBLE);
                    echeack.setText("enter valid email");
                Email.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        echeack.setVisibility(View.INVISIBLE);

                        return false;
                    }
                });
                }
                if(isNameValid(name)!=true){
                    nameb=0;
                    ncheack.setVisibility(View.VISIBLE);
                    ncheack.setText("enter valid name");
                    Name.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            ncheack.setVisibility(View.INVISIBLE);

                            return false;
                        }
                    });
                }
                if(!p.equals(cpassword))
                {
                    pass=0;
                    pcheack.setVisibility(View.VISIBLE);
                    pcheack.setText("correctly confirm password");
                    CPassword.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            pcheack.setVisibility(View.INVISIBLE);

                            return false;
                        }
                    });
                }
                if(pass==1&&nameb==1&&mail==1){
                mauth.createUserWithEmailAndPassword(email,cpassword)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUp.this,"SignUp Successfully Done",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    {
                                        Toast.makeText(SignUp.this,"SignUp Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }


            }
        });
    }

    public static boolean isNameValid(String name)
    {
        String expression = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}