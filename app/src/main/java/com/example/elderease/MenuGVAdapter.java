package com.example.elderease;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.List;

public class MenuGVAdapter extends BaseAdapter {
    List<Menu> menuList;
    Context context;
    LayoutInflater inflater;

    String[] colors = {"#ACDBEF", "#E9A3A7", "#ACDBEF", "#8F8F8F"};
    int[] colorInt = {0xACDBEF, 0xE9A3A7, 0xACDBEF, 0x8F8F8F};
    public MenuGVAdapter(Context context, List<Menu> inputMenuList){
        this.context = context;
        this.menuList = inputMenuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int i) {
        return menuList.get(i);
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
            view = inflater.inflate(R.layout.menu_item, null);
        }

        Button menuBtn = view.findViewById(R.id.menuBtn);
//        ConstraintLayout menuCL = view.findViewById(R.id.menuCL);

        Drawable icon = ContextCompat.getDrawable(context, menuList.get(i).getIconId());
        menuBtn.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null);
        menuBtn.setText(menuList.get(i).getText());
        menuBtn.setTextColor(Color.parseColor(colors[i]));

//        menuBtn.setBackgroundResource(R.drawable.menu_btn_background);

//        menuBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.menu_btn_background));

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuList.get(i).getText().equals("Telepon Darurat")){
                    Intent toECall = new Intent(context, EmergencyCallActivity.class);
                    context.startActivity(toECall);
                }
                else if(menuList.get(i).getText() == "Baca Tulisan"){
                    //BENERIN
//                    Intent toECall = new Intent(context, EmergencyCallActivity.class);
//                    context.startActivity(toECall);
                }
                else if(menuList.get(i).getText() == "Notifikasi"){
                    Intent toReminder = new Intent(context, ReminderActivity.class);
                    context.startActivity(toReminder);
                }
                else if(menuList.get(i).getText() == "Pengaturan"){
//                    Intent toECall = new Intent(context, EmergencyCallActivity.class);
//                    context.startActivity(toECall);
                }
            }
        });
        return view;
    }
}
