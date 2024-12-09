package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText registerNameET = findViewById(R.id.registerNameET);
        EditText registerPassET = findViewById(R.id.registerPassET);
        EditText registerConfirmPassET = findViewById(R.id.registerConfirmPassET);
        EditText registerPhoneNumET = findViewById(R.id.registerPhoneNumET);
        Button registerNextBtn = findViewById(R.id.registerNextBtn);
        TextView registerToLogin2TV = findViewById(R.id.registerToLogin2TV);

        String username=registerNameET.getText().toString();
        String pass = registerPassET.getText().toString();
        String confirmPass = registerConfirmPassET.getText().toString();
        String phoneNum = registerPhoneNumET.getText().toString();

        Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        Intent toOTP = new Intent(RegisterActivity.this, OTPActivity.class);
        registerNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toOTP);
            }
        });

        registerToLogin2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toLogin);
            }
        });

    }
}