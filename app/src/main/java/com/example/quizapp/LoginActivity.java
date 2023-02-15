package com.example.quizapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton  ButtonLogin;
    TextView ButtonsignUp;
    TextInputEditText  Editemail;
    TextInputEditText Editpassword;
    FirebaseAuth authprofile;
    ProgressBar progressBar;
    private static final String TAG ="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        //initialising variables
        progressBar = findViewById(R.id.progressbarlogin);
        Editemail = findViewById(R.id.email);
        Editpassword = findViewById(R.id.password);
        ButtonLogin = findViewById(R.id.loginbutton);
        ButtonsignUp = findViewById(R.id.Signup);
        authprofile = FirebaseAuth.getInstance();
        ButtonsignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);
        });

        ButtonLogin.setOnClickListener(view -> {
            //obtain data from textfields
            String email = Editemail.getText().toString();
            String password = Editpassword.getText().toString();
            //check if data entered is empty
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                Editemail.setError("Email required");
                Editemail.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please reenter password", Toast.LENGTH_SHORT).show();
                Editemail.setError("Invalid email");
                Editemail.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                Editpassword.setError("Password required");
                Editpassword.requestFocus();
            }else{
                progressBar.setVisibility(View.VISIBLE);
                LoginUser(email, password);
            }
        });
    }
    private void LoginUser(String email, String password) {
     authprofile.signInWithEmailAndPassword(email , password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 Intent intent = new Intent(LoginActivity.this,ExamPractice.class);
                 startActivity(intent);
                 finish();
                 Toast.makeText(LoginActivity.this, "You are logged in now", Toast.LENGTH_SHORT).show();
             }else{
                 try{
                     throw task.getException();
                 }catch(FirebaseAuthInvalidUserException e){
                     Editemail.setError("User does not exist or is no longer valid, please enter again");
                     Editemail.requestFocus();
                 }catch (FirebaseAuthInvalidCredentialsException e){
                     Editemail.setError("Invalid credentials.Kindly, check and re enter");
                     Editemail.requestFocus();
                 }catch (Exception e){
                     Log.e(TAG, e.getMessage());
                     Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                 }
             }
             progressBar.setVisibility(View.GONE);
         }

     });
    }
}