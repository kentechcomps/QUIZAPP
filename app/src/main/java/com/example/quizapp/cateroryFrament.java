package com.example.quizapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import com.example.quizapp.Categorymodel;
import com.example.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class cateroryFrament extends Fragment {


    public cateroryFrament() {
        // Required empty public constructor
    }
    private GridView catview;
    public static List<Categorymodel> catlist = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_caterory_frament, container, false);
        catview = view.findViewById(R.id.catgrid);
        loadCategories();
        Categoryadapter adapter = new Categoryadapter(catlist);
        catview.setAdapter(adapter);

        return view;
    }
    private void loadCategories()
    {
        catlist.clear();

        catlist.add(new Categorymodel("1","GK",25));
        catlist.add(new Categorymodel("2","History",30));
        catlist.add(new Categorymodel("3","English",10));
        catlist.add(new Categorymodel("4","Science",25));
        catlist.add(new Categorymodel("1","MATHS",20));

    }
}