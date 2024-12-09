package com.example.elderease;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import com.google.mlkit.vision.common.InputImage;
//import com.google.mlkit.vision.text.Text;
//import com.google.mlkit.vision.text.TextRecognition;
//import com.google.mlkit.vision.text.TextRecognizer;

//import com.google.android.gms.vision.Frame;
//import com.google.android.gms.vision.text.TextBlock;
//import com.google.android.gms.vision.text.TextRecognizer;

public class ReadTextActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int PICK_IMAGE = 1;
    private static final int CROP_IMAGE = 2;
    private static final int STORAGE_PERMISSION_CODE = 200;


    TextView readTextResTV;
    ImageView readTextPlayIV;
    Button readTextCaptureBtn;
    Uri imageUri;
    ActivityResultLauncher<Uri> activityResultLauncher;
    TextRecognizer recognizer;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_text);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recognizer =  TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        tts = new TextToSpeech(ReadTextActivity.this, new TextToSpeech.OnInitListener() {
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
        readTextResTV = findViewById(R.id.readTextResTV);
        readTextPlayIV = findViewById(R.id.readTextPlayIV);
        readTextCaptureBtn = findViewById(R.id.readTextCaptureBtn);
        ImageView readTextBackIV = findViewById(R.id.readTextBackIV);
        Button readTextOpenGalleryBtn = findViewById(R.id.readTextOpenGalleryBtn);

        imageUri = createUri();
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if(result){
                                try {
                                    InputImage image = InputImage.fromFilePath(ReadTextActivity.this, imageUri);

                                    Task<Text> res =
                                            recognizer.process(image)
                                                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                                                        @Override
                                                        public void onSuccess(Text visionText) {
                                                            String recognizeText = visionText.getText();
                                                            readTextResTV.setText(recognizeText);
                                                            if(!recognizeText.isEmpty()){
                                                                readTextPlayIV.setColorFilter(Color.parseColor("#1596FF"), PorterDuff.Mode.SRC_IN);
                                                                readTextCaptureBtn.setBackgroundColor(Color.parseColor("#90CAF9"));
                                                            }
                                                        }
                                                    })
                                                    .addOnFailureListener(
                                                            new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(ReadTextActivity.this,"no text detected", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }
                }
        );

        Intent back = new Intent(ReadTextActivity.this, MainActivity.class);
        readTextBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tts.isSpeaking()) {
                    tts.stop();
                }
                startActivity(back);
                finish();
            }
        });

        readTextCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCameraPermission();
            }
        });
        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction
                new int[]{Color.WHITE, Color.WHITE}      // Start and end colors
        );
        gradient.setCornerRadius(17f); // Rounded corners
        gradient.setStroke(4, Color.parseColor("#1596FF"));

        readTextOpenGalleryBtn.setBackgroundTintList(null);
        readTextOpenGalleryBtn.setBackground(gradient);
        readTextOpenGalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ReadTextActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReadTextActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                }

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);

            }
        });

        readTextPlayIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = readTextResTV.getText().toString();
                if (tts.isSpeaking()) {
                    readTextPlayIV.setImageResource(R.drawable.baseline_play_circle_24);
                    tts.stop();
                }
                else if(!text.isEmpty()){
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    readTextPlayIV.setImageResource(R.drawable.baseline_pause_circle_24);
                }
                else if(text.isEmpty()){
                    Toast.makeText(ReadTextActivity.this, "Tidak ada tulisan yang terdeteksi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Uri createUri(){
        File imageFile = new File(getApplicationContext().getFilesDir(), "camera_photo.jpg");
        return FileProvider.getUriForFile(getApplicationContext(), "com.example.elderease.fileProvider",imageFile);
    }
    private void checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }else {
            activityResultLauncher.launch(imageUri);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                activityResultLauncher.launch(imageUri);
            } else {
                Toast.makeText(this, "Please allow camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(imageUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("return-data", true);

        startActivityForResult(cropIntent, CROP_IMAGE);
    } else if (requestCode == CROP_IMAGE && resultCode == RESULT_OK && data != null) {
        Bundle extras = data.getExtras();
            Bitmap croppedBitmap = extras.getParcelable("data"); // Get the cropped Bitmap

            // Save the Bitmap to a file
            try {
                File file = new File(getCacheDir(), "cropped_image.jpg");
                FileOutputStream out = new FileOutputStream(file);
                croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.close();

                // Get the file's URI or path
                Uri croppedImageUri = Uri.fromFile(file);
                InputImage croppedImage = InputImage.fromFilePath(ReadTextActivity.this, croppedImageUri);

                Task<Text> res =
                        recognizer.process(croppedImage)
                                .addOnSuccessListener(new OnSuccessListener<Text>() {
                                    @Override
                                    public void onSuccess(Text visionText) {
                                        String recognizeText = visionText.getText();
                                        readTextResTV.setText(recognizeText);
                                        if(!recognizeText.isEmpty()){
                                            readTextPlayIV.setColorFilter(Color.parseColor("#1596FF"), PorterDuff.Mode.SRC_IN);
                                            readTextCaptureBtn.setBackgroundColor(Color.parseColor("#90CAF9"));
                                        }
                                    }
                                })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(ReadTextActivity.this,"no text detected", Toast.LENGTH_SHORT).show();
                                            }
                                        });

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    }
}