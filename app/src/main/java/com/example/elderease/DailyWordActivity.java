package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DailyWordActivity extends AppCompatActivity {
    List<String> dailyWordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_word);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dailyWordCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView dailyWordRV = findViewById(R.id.dailyWordRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dailyWordRV.setLayoutManager(layoutManager);

        dailyWordList = new ArrayList<>();
        dailyWordList.add("Iya");
        dailyWordList.add("Tidak");
        dailyWordList.add("Makan");
        dailyWordList.add("Toilet");
        DailyWordRVAdapter adapter = new DailyWordRVAdapter(dailyWordList);
        dailyWordRV.setAdapter(adapter);

        ImageView dailyWordBackIV = findViewById(R.id.dailyWordBackIV);
        ImageView dailyWordEditIV = findViewById(R.id.dailyWordEditIV);
        Button dailyWordAddBtn = findViewById(R.id.dailyWordAddBtn);

        dailyWordBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(DailyWordActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
        dailyWordEditIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editDailyWord = new Intent(DailyWordActivity.this, EditDailyWordActivity.class);
                startActivity(editDailyWord);
            }
        });


        ConstraintLayout dailyWordCL = findViewById(R.id.dailyWordCL);
        Button dailyWordAddWordBtn = findViewById(R.id.dailyWordAddWordBtn);
        EditText dailyWordNewWordET = findViewById(R.id.dailyWordNewWordET);
        String newWord = dailyWordNewWordET.getText().toString();

        ConstraintSet initialSet = new ConstraintSet();
        ConstraintSet finalSet = new ConstraintSet();
        initialSet.clone(dailyWordCL); // Initial state (card off-screen)
        finalSet.clone(dailyWordCL);   // Modify for the card to slide up

        // Adjust the bottom constraint of the card for the final state
        finalSet.setTranslationY(R.id.dailyWordSC, 0);
        dailyWordAddBtn.setOnClickListener(v -> {
            TransitionManager.beginDelayedTransition(dailyWordCL, new ChangeBounds());
            finalSet.applyTo(dailyWordCL);
        });
        dailyWordAddWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newWord.isEmpty()){
                    Toast.makeText(DailyWordActivity.this, newWord+" added", Toast.LENGTH_SHORT).show();
                    dailyWordList.add(newWord);
                }
                TransitionManager.beginDelayedTransition(dailyWordCL, new ChangeBounds());
                initialSet.applyTo(dailyWordCL);
            }
        });
    }
}