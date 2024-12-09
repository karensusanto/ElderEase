package com.example.elderease;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
    int[][] colors = {
            {0xFFA4F3CE, 0xFF6CE0A9},
            {0xFFE9A3A7, 0xFFE06C73},
            {0xFFEFC1A0, 0xFFF8AD79},
            {0xFFACDBEF, 0xFF76CDF2}
    };
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
        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction
                new int[]{colors[position%4][0], colors[position%4][1]}      // Start and end colors
        );
        gradient.setCornerRadius(16f); // Rounded corners
        holder.wordBtn.setBackgroundTintList(null);
        holder.wordBtn.setBackground(gradient);
    }

    @Override
    public int getItemCount() {
        return dailyWordList.size();
    }
}
