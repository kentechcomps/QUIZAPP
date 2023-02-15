package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TestActivities extends AppCompatActivity {
     Toolbar toolbar ;
     RecyclerView testView;
     private List<Testmodel> testlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activities);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        int cat_index = getIntent().getIntExtra("CAT_INDEX",0);
        getSupportActionBar().setTitle(cateroryFrament.catlist.get(cat_index).getName());
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //setting up recyclerview
        testView = findViewById(R.id.testview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
         testView.setLayoutManager(layoutManager);
         loadTestData();
         TestAdapter adapter = new TestAdapter(testlist);
         testView.setAdapter(adapter);
    }

    private void loadTestData() {
        testlist = new ArrayList<>();
        testlist.add(new Testmodel("1",50,20));
        testlist.add(new Testmodel("2",80,20));
        testlist.add(new Testmodel("3",0,25));
        testlist.add(new Testmodel("4",10,40));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            TestActivities.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}