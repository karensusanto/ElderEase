package com.example.elderease;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EditDailyWordRVAdapter extends RecyclerView.Adapter<EditDailyWordRVAdapter.EditDailyWordHolder> {
    Context context;
    List<String> dailyWordList;
    int[][] colors = {
            {0xFFA4F3CE, 0xFF6CE0A9},
            {0xFFE9A3A7, 0xFFE06C73},
            {0xFFEFC1A0, 0xFFF8AD79},
            {0xFFACDBEF, 0xFF76CDF2}
    };
    public EditDailyWordRVAdapter(List<String> inputDailyWordList){
        dailyWordList = inputDailyWordList;
    }
    public class EditDailyWordHolder extends RecyclerView.ViewHolder{
        Button wordBtn;
        ImageButton delBtn;
        public EditDailyWordHolder(@NonNull View itemView) {
            super(itemView);
            wordBtn = itemView.findViewById(R.id.editListItemNameBtn);
            delBtn = itemView.findViewById(R.id.editListItemDelBtn);
        }
    }
    @NonNull
    @Override
    public EditDailyWordRVAdapter.EditDailyWordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.edit_list_item, parent, false);
        EditDailyWordRVAdapter.EditDailyWordHolder holder = new EditDailyWordRVAdapter.EditDailyWordHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditDailyWordRVAdapter.EditDailyWordHolder holder, int position) {
        String name = dailyWordList.get(position);
        holder.wordBtn.setText(name);
        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction
                new int[]{colors[position%4][0], colors[position%4][1]}      // Start and end colors
        );
        gradient.setCornerRadius(16f); // Rounded corners
        holder.wordBtn.setBackgroundTintList(null);
        holder.wordBtn.setBackground(gradient);


        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //apus dr db
                Toast.makeText(context, name+" deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dailyWordList.size();
    }
}
