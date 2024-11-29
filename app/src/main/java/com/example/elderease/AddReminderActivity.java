package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_reminder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView addReminderBackIV = findViewById(R.id.addReminderBackIV);
        Intent back = new Intent(AddReminderActivity.this, ReminderActivity.class);
        addReminderBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(back);
                finish();
            }
        });

        EditText addReminderTimeET = findViewById(R.id.addReminderTimeET);
        EditText addReminderTextET = findViewById(R.id.addReminderTextET);
        String time = addReminderTimeET.getText().toString();
        String text = addReminderTextET.getText().toString();
        Button addReminderSaveBtn = findViewById(R.id.addReminderSaveBtn);
        addReminderSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!time.isEmpty() && !text.isEmpty()){
                    back.putExtra("newTime", time);
                    back.putExtra("newText", text);
                    Toast.makeText(AddReminderActivity.this, "new reminder added", Toast.LENGTH_SHORT).show();
                    startActivity(back);
                    finish();
                }
                else if(time.isEmpty()){
                    Toast.makeText(AddReminderActivity.this, "fill time field", Toast.LENGTH_SHORT).show();
                }
                else if(text.isEmpty()){
                    Toast.makeText(AddReminderActivity.this, "fill reminder field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}