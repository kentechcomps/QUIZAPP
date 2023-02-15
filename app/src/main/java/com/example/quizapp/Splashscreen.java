package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {

    TextView appname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        appname = findViewById(R.id.competition);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);
        appname.setTypeface(typeface);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.myanim);
        appname.setAnimation(animation);
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Splashscreen.this, ExamPractice.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}

      //  new Handler().postDelayed(new Runnable() {
       //     @Override
         //   public void run() {
 //Intent intent = new Intent (MainActivity.this,Dashboard.class);
 //startActivity(intent);
      //      }
     //   }, 1500);
   // }
//}