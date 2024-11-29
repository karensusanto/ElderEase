package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class EditDailyWordActivity extends AppCompatActivity {
    List<String>dailyWordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_daily_word);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dailyWordCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView editDailyWordBackBtn = findViewById(R.id.editDailyWordBackBtn);
        editDailyWordBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(EditDailyWordActivity.this, DailyWordActivity.class);
                startActivity(back);
                finish();
            }
        });

        RecyclerView editDailyWordRV = findViewById(R.id.editDailyWordRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        editDailyWordRV.setLayoutManager(layoutManager);

        dailyWordList = new ArrayList<>();
        dailyWordList.add("Iya");
        dailyWordList.add("Tidak");
        dailyWordList.add("Makan");
        dailyWordList.add("Toilet");
        EditDailyWordRVAdapter adapter = new EditDailyWordRVAdapter(dailyWordList);
        editDailyWordRV.setAdapter(adapter);
    }
}