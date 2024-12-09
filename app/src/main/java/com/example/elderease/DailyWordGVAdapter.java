package com.example.elderease;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;
import java.util.Locale;

public class DailyWordGVAdapter extends BaseAdapter {
    Context context;
    List<String> dailyWordList;
    LayoutInflater inflater;
    int[][] colors = {
            {0xFFA4F3CE, 0xFF6CE0A9},
            {0xFFE9A3A7, 0xFFE06C73},
            {0xFFEFC1A0, 0xFFF8AD79},
            {0xFFACDBEF, 0xFF76CDF2}
    };
    TextToSpeech tts;

    public DailyWordGVAdapter(Context context, List<String> inputDailyWords){
        this.context = context;
        this.dailyWordList = inputDailyWords;
    }
    @Override
    public int getCount() {
        return dailyWordList.size();
    }

    @Override
    public Object getItem(int i) {
        return dailyWordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.daily_word, null);
        }

        Button wordBtn = view.findViewById(R.id.dailyWordBtn);

        wordBtn.setText(dailyWordList.get(i));
        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction
                new int[]{colors[i%4][0], colors[i%4][1]}      // Start and end colors
        );
        gradient.setCornerRadius(16f); // Rounded corners
        wordBtn.setBackgroundTintList(null);
        wordBtn.setBackground(gradient);

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Locale indonesian = new Locale("id", "ID");
                    tts.setLanguage(indonesian);
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        },"com.google.android.tts");
        wordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = wordBtn.getText().toString();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        return view;
    }
}
