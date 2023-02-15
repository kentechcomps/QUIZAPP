package com.example.quizapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Categoryadapter extends BaseAdapter {

    private List<Categorymodel> catlistt;

    public Categoryadapter(List<Categorymodel> catlistt) {
        this.catlistt = catlistt;
    }

    @Override
    public int getCount() {
        return catlistt.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview;
        if (view == null) {
            myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categoryitemlayout, viewGroup, false);
        } else {
            myview = view;
        }
          myview.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(view.getContext(),TestActivities.class);
                  intent.putExtra("CAT_INDEX",i);
                  view.getContext().startActivity(intent);

              }
          });
        TextView catNAME = myview.findViewById(R.id.catname);
        TextView  noofTests = myview.findViewById(R.id.numberoftext);

        catNAME.setText(catlistt.get(i).getName());
        noofTests.setText(String.valueOf(catlistt.get(i).getNoOFTEXT()));

        return myview;
    }
}
