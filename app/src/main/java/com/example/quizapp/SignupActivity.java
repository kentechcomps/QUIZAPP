package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private TextInputEditText TextInputEditTextfullnames;
    private TextInputEditText TextInputEditTextemail;
    private TextInputEditText TextInputEditTextpassword;
    private TextInputEditText TextInputEditTextconfirmpassword;
    private AppCompatButton AppCompatButtonsignup;
    private ProgressBar ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        TextInputEditTextfullnames = findViewById(R.id.fullnames);
        TextInputEditTextemail = findViewById(R.id.Emailsignup);
        TextInputEditTextpassword = findViewById(R.id.passwordsignup);
        TextInputEditTextconfirmpassword = findViewById(R.id.confirmpasswordsignup);
        AppCompatButtonsignup = findViewById(R.id.signupbutton);
        ProgressBar = findViewById(R.id.progressbar);

        AppCompatButtonsignup.setOnClickListener(view -> {
            //obtain the entered data
            String fullnames = TextInputEditTextfullnames.getText().toString();
            String email = TextInputEditTextemail.getText().toString();
            String password = TextInputEditTextpassword.getText().toString();
            String confirmpassword = TextInputEditTextconfirmpassword.getText().toString();
            if (TextUtils.isEmpty(fullnames)) {
                Toast.makeText(SignupActivity.this, "Please enter your fullnames", Toast.LENGTH_SHORT).show();
                TextInputEditTextfullnames.setError("Fullnames Required");
                TextInputEditTextfullnames.requestFocus();
            } else if (TextUtils.isEmpty(email)) {
                Toast.makeText(SignupActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                TextInputEditTextemail.setError("Email is required");
                TextInputEditTextemail.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(SignupActivity.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                TextInputEditTextpassword.setError("Enter valid email");
                TextInputEditTextemail.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(SignupActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                TextInputEditTextpassword.setError("Password required");
                TextInputEditTextpassword.requestFocus();
            } else if (password.length() < 8) {
                Toast.makeText(SignupActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                TextInputEditTextpassword.setError("Renter Password");
                TextInputEditTextpassword.requestFocus();
            } else if (TextUtils.isEmpty(confirmpassword)) {
                Toast.makeText(SignupActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                TextInputEditTextconfirmpassword.setError("Password does not match");
                TextInputEditTextconfirmpassword.requestFocus();
            } else if (!password.equals(confirmpassword)) {
                Toast.makeText(SignupActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                TextInputEditTextconfirmpassword.setError("Re-enter password");
                TextInputEditTextconfirmpassword.requestFocus();
                //Clear the entered passwords
                TextInputEditTextpassword.clearComposingText();
                TextInputEditTextconfirmpassword.clearComposingText();
            } else {
                registerUser(fullnames, email, password, confirmpassword);
            }
        });

    }
    //Register using the credentials given
    private void registerUser(String fullnames, String email, String password, String confirmpassword) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "User registered succefully", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseuser = auth.getCurrentUser();

                    //send Verification Email
                    firebaseuser.sendEmailVerification();
                    //Open Exam Practice
                    Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                    //  To prevent user from returning back to Signup Activity on pressing back button after registration
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();  //to close Signup Activity
                }
            }
        });

    }

}