package com.example.elderease;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DailyWordRVAdapter extends RecyclerView.Adapter<DailyWordRVAdapter.DailyWordHolder> {
    Context context;
    List<String> dailyWordList;
    String[] colors = {"#A4F3CE", "#E9A3A7", "#EFC1A0", "#ACDBEF"};
    public DailyWordRVAdapter(List<String> inputDailyWordList){
        dailyWordList = inputDailyWordList;
    }
    public class DailyWordHolder extends RecyclerView.ViewHolder{
        Button wordBtn;
        public DailyWordHolder(@NonNull View itemView) {
            super(itemView);
            wordBtn = itemView.findViewById(R.id.listItemBtn);
        }
    }
    @NonNull
    @Override
    public DailyWordRVAdapter.DailyWordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        DailyWordRVAdapter.DailyWordHolder holder = new DailyWordRVAdapter.DailyWordHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWordRVAdapter.DailyWordHolder holder, int position) {
        String word = dailyWordList.get(position);
        holder.wordBtn.setText(word);
        holder.wordBtn.setBackgroundColor(Color.parseColor(colors[position%4]));
    }

    @Override
    public int getItemCount() {
        return dailyWordList.size();
    }
}
