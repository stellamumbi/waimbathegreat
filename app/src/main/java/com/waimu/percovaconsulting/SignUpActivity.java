package com.waimu.percovaconsulting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.view.View.GONE;


public class SignUpActivity extends AppCompatActivity {
    private EditText email,password;
    private Button sign_up_button,btn_reset_password,login_in_button;
    private ProgressBar progress_bar;
    private FirebaseAuth Auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //firebase auth instance
        Auth= FirebaseAuth.getInstance();

        login_in_button=findViewById(R.id.login_in_button);
        btn_reset_password=findViewById(R.id.btn_reset_password);
        sign_up_button=findViewById(R.id.sign_up_button);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        progress_bar=findViewById(R.id.progress_bar);

        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,ResetActivity.class));
            }
        });
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId= email.getText().toString().trim();
                String passwordId=password.getText().toString().trim();


              if  ( TextUtils.isEmpty(emailId)){
                  Toast.makeText(SignUpActivity.this,"Enter Email Address",Toast.LENGTH_SHORT).show();

              }
              if  (TextUtils.isEmpty(passwordId)){
                  Toast.makeText(SignUpActivity.this , "Enter Password", Toast.LENGTH_SHORT).show();

              }
              if (password.length()<8){
                  Toast.makeText(getApplicationContext(),"Password too short,enter a minimum of 8 letters",Toast.LENGTH_SHORT).show();
              }
              progress_bar.setVisibility(View.VISIBLE);
                  // create user
                Auth.createUserWithEmailAndPassword(emailId,passwordId)
                     .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             Toast.makeText(getApplicationContext(),"create user with email:onComplete +task isSuccessful()",Toast.LENGTH_SHORT).show();
                                 progress_bar.setVisibility( View.GONE);

                                 if(!task .isSuccessful()){
                                     Toast.makeText(getApplicationContext(),"Authentication Failed +task.getException()",Toast.LENGTH_SHORT).show();

                                 }
                                 else{
                                     startActivity ( new Intent(SignUpActivity.this, MainActivity.class));
                                     finish();
                                 }




                         }
                     });









            }
        });








    }
}
