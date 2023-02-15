package com.example.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.viewHolder>{
    private List<Testmodel> testlist;
//constractor
     public TestAdapter(List<Testmodel> testlist) {
        this.testlist = testlist;
    }

    @NonNull
    @Override
    public TestAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_itemlayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.viewHolder holder, int position) {
          int progress = testlist.get(position).getTopScore();
          holder.setData(position,progress);
    }

    @Override
    public int getItemCount() {
        return testlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView testNo;
        private TextView topscorer;
        private ProgressBar progressBar;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
           testNo= itemView.findViewById(R.id.testNo);
           topscorer = itemView.findViewById(R.id.scoretext);
           progressBar = itemView.findViewById(R.id.progressBar);
        }
        private void setData(int pos , int progress){
        testNo.setText("Test no: " + String.valueOf(pos +1));
        topscorer.setText(String.valueOf(progress)+"%");
        progressBar.setProgress(progress);
        }
    }
}
