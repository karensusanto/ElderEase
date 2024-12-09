package com.example.elderease;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MenuGVAdapter extends BaseAdapter {
    private static final int CAMERA_PERMISSION_CODE = 100;
    Uri imageUri;
    List<Menu> menuList;
    Context context;
    LayoutInflater inflater;
    String[] colors = {"#ACDBEF", "#E9A3A7", "#8F8F8F"};
//    int[] colorInt = {0xACDBEF, 0xE9A3A7, 0xACDBEF, 0x8F8F8F};
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

        Drawable icon = ContextCompat.getDrawable(context, menuList.get(i).getIconId());
        menuBtn.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null);
        menuBtn.setText(menuList.get(i).getText());
        menuBtn.setTextColor(Color.parseColor(colors[i]));

        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction
                new int[]{Color.WHITE, Color.WHITE}      // Start and end colors
        );
        gradient.setCornerRadius(16f); // Rounded corners
        gradient.setStroke(5, Color.parseColor(colors[i]));
        menuBtn.setBackgroundTintList(null);
        menuBtn.setBackground(gradient);

        menuBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(menuList.get(i).getText().equals("Telepon Darurat")){
                    Intent toECall = new Intent(context, EmergencyCallActivity.class);
                    context.startActivity(toECall);
                }
                else if(menuList.get(i).getText() == "Baca Tulisan"){
                    Intent toReadText = new Intent(context, ReadTextActivity.class);
                    context.startActivity(toReadText);
                }
                else if(menuList.get(i).getText() == "Notifikasi"){
                    Intent toReminder = new Intent(context, ReminderActivity.class);
                    context.startActivity(toReminder);
                }
            }
        });
        return view;
    }


}
