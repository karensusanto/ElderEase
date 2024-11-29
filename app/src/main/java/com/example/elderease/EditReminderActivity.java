package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_reminder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editReminderTimeET = findViewById(R.id.editReminderTimeET);
        EditText editReminderTextET = findViewById(R.id.editReminderTextET);
        Button editReminderSaveBtn = findViewById(R.id.editReminderSaveBtn);
        ImageView editReminderBackIV = findViewById(R.id.editReminderBackIV);

        Intent getData = getIntent();
        int id = getData.getIntExtra("id", 0);
        String time = getData.getStringExtra("time");
        String text = getData.getStringExtra("text");
        editReminderTimeET.setText(time);
        editReminderTextET.setText(text);

        Intent back = new Intent(EditReminderActivity.this, ReminderActivity.class);
        editReminderSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.putExtra("id", id);
                back.putExtra("time", editReminderTimeET.getText());
                back.putExtra("text", editReminderTextET.getText());
                startActivity(back);
                finish();
            }
        });
        editReminderBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(back);
                finish();
            }
        });
    }
}