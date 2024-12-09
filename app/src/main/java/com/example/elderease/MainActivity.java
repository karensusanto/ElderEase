package com.example.elderease;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    List<String> dailyWordList;
    List<Menu> menuList;
    GridView dailyWordGV, menuGV;
    MenuGVAdapter menuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dailyWordCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dailyWordGV = findViewById(R.id.mainDailyWordGV);
        initializeDailyWord();
        DailyWordGVAdapter activityAdapter = new DailyWordGVAdapter(MainActivity.this, dailyWordList);
        dailyWordGV.setAdapter(activityAdapter);
        setGridViewHeightBasedOnChildren(dailyWordGV, (DailyWordGVAdapter) dailyWordGV.getAdapter());


        initializeMenu();
        menuGV = findViewById(R.id.mainMenuGrid);
        menuAdapter = new MenuGVAdapter(MainActivity.this, menuList);
        menuGV.setAdapter(menuAdapter);
        setGridViewHeightBasedOnChildren(menuGV, (MenuGVAdapter) menuGV.getAdapter());

        Button mainAddDailyWordBtnBtn = findViewById(R.id.mainAddDailyWordBtn);
        mainAddDailyWordBtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dailyWordPage = new Intent(MainActivity.this, DailyWordActivity.class);
                startActivity(dailyWordPage);
            }
        });
    }

    public void initializeDailyWord(){
        dailyWordList = new ArrayList<>();
        dailyWordList.add("Iya");
        dailyWordList.add("Tidak");
        dailyWordList.add("Makan");
        dailyWordList.add("Toilet");
    }

    public void initializeMenu(){
        menuList = new ArrayList<>();
        menuList.add(new Menu(R.drawable.scanner, "Baca Tulisan"));
        menuList.add(new Menu(R.drawable.phone, "Telepon Darurat"));
        menuList.add(new Menu(R.drawable.notifications, "Notifikasi"));
    }

    public void setGridViewHeightBasedOnChildren(GridView gridView, ListAdapter adapter) {
        if (adapter == null) return;

        int totalHeight = 0;
        int items = adapter.getCount();
        int rows = (int) Math.ceil((double) items / 2);

        for (int i = 0; i < rows; i++) {
            View listItem = adapter.getView(i, null, gridView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        totalHeight += (gridView.getVerticalSpacing() * (rows - 1));

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }
}