package com.example.quizapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

public class ExamPractice extends AppCompatActivity {

        private BottomNavigationView bottomNavigationView;
        private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_exam_practice);

        // initialise variables

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);
        setfragment(new cateroryFrament());
// Set listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle the click on the menu item
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setfragment(new cateroryFrament());
                        return true;
                    case R.id.leaderboard:
                        setfragment(new Leaderboardfragment());
                        return true;
                    case R.id.account:
                        setfragment(new AccountFragment());
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
    private void setfragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(),fragment);
        transaction.commit();
    }
}
