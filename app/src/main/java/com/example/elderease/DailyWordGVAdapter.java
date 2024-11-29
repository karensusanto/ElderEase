package com.example.elderease;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class DailyWordGVAdapter extends BaseAdapter {
    Context context;
    List<String> dailyWordList;
    LayoutInflater inflater;
//    int[][] colors = {
//            {0xA4F3CE, 0x6CE0A9},
//            {0xE9A3A7, 0xE06C73},
//            {0xEFC1A0, 0xF8AD79},
//            {0xACDBEF, 0x76CDF2}
//    };

    String[] colors = {"#A4F3CE", "#E9A3A7", "#EFC1A0", "#ACDBEF"};

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
        wordBtn.setBackgroundColor(Color.parseColor(colors[i%4]));
//        GradientDrawable gradientDrawable = new GradientDrawable(
//                GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction (diagonal)
//                colors[i%4]
//        );
//
//        // Set gradient type (optional, defaults to linear)
//        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
//
//        // Apply the gradient as the button's background
//        wordBtn.setBackground(gradientDrawable);

        wordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                hrs bs ngomong teksnya somehow
            }
        });
        return view;
    }
}
