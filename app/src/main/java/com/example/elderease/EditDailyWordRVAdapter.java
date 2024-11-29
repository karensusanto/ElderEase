package com.example.elderease;

import android.content.Context;
import android.graphics.Color;
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
    String[] colors = {"#A4F3CE", "#E9A3A7", "#EFC1A0", "#ACDBEF"};
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
        holder.wordBtn.setBackgroundColor(Color.parseColor(colors[position%4]));


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
