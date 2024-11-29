package com.example.elderease;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderRVAdapter extends RecyclerView.Adapter<ReminderRVAdapter.ReminderHolder> {
    Context context;
    List<Reminder> reminderList;
    public ReminderRVAdapter(List<Reminder> inputReminderList){
        reminderList = inputReminderList;
    }
    public class ReminderHolder extends RecyclerView.ViewHolder{
        Button reminderItemBtn;
        ImageButton reminderItemDelBtn;
        ConstraintLayout reminderItemCL;
        public ReminderHolder(@NonNull View itemView) {
            super(itemView);
            reminderItemBtn = itemView.findViewById(R.id.reminderItemBtn);
            reminderItemDelBtn = itemView.findViewById(R.id.reminderItemDelBtn);
            reminderItemCL = itemView.findViewById(R.id.reminderItemCL);
        }
    }
    @NonNull
    @Override
    public ReminderRVAdapter.ReminderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.reminder_item, parent, false);
        ReminderHolder holder = new ReminderHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderRVAdapter.ReminderHolder holder, int position) {
        String time = reminderList.get(position).getTime();
        String text = reminderList.get(position).getText();
        holder.reminderItemBtn.setText(time+"    "+text);

        holder.reminderItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editReminder = new Intent(context, EditReminderActivity.class);
                editReminder.putExtra("id", position);
                editReminder.putExtra("time", time);
                editReminder.putExtra("text", text);
                context.startActivity(editReminder);
            }
        });

        holder.reminderItemDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Reminder '"+text+"' deleted", Toast.LENGTH_SHORT).show();
                reminderList.remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }
}
