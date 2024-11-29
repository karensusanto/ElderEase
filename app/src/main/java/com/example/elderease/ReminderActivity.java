package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {
    List<Reminder> reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reminder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView reminderRV = findViewById(R.id.reminderRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reminderRV.setLayoutManager(layoutManager);

        populateReminder();
        Intent getData = getIntent();
        int id = getData.getIntExtra("id",0);
        String time = getData.getStringExtra("time");
        String text = getData.getStringExtra("text");

        String newTime = getData.getStringExtra("newTime");
        String newText = getData.getStringExtra("newText");
        if(text != null){
            Reminder updateReminder = new Reminder(time, text);
            reminderList.set(id, updateReminder);
        }
        if(newText != null){
            reminderList.add(new Reminder(newTime, newText));
        }
        ReminderRVAdapter adapter = new ReminderRVAdapter(reminderList);
        reminderRV.setAdapter(adapter);

        ImageView reminderBackIV = findViewById(R.id.reminderBackIV);
        reminderBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ReminderActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        Button reminderAddBtn = findViewById(R.id.reminderAddBtn);
        reminderAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addReminder = new Intent(ReminderActivity.this, AddReminderActivity.class);
                startActivity(addReminder);
            }
        });
    }

    public void populateReminder(){
        reminderList = new ArrayList<>();
        reminderList.add(new Reminder("07.30", "Sarapan"));
        reminderList.add(new Reminder("12.00", "Makan Siang"));
        reminderList.add(new Reminder("15.00", "Cemilan"));
    }
}