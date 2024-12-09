package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dailyWordCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView addContactBackIV = findViewById(R.id.ecallBackIV);
        Button addContactBtn = findViewById(R.id.addContactBtn);
        EditText addContactNameET = findViewById(R.id.addContactNameET);
        EditText addContactPhoneNumET = findViewById(R.id.addContactPhoneNumET);



        Intent back = new Intent(AddContactActivity.this, EmergencyCallActivity.class);
        addContactBackIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(back);
                finish();
            }
        });
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addContactNameET.getText().toString();
                String phoneNum = addContactPhoneNumET.getText().toString();
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phoneNum)){
                    Intent addContact = new Intent(AddContactActivity.this, EmergencyCallActivity.class);
                    //update db
                    Toast.makeText(AddContactActivity.this, "Kontak Baru Ditambahkan",Toast.LENGTH_SHORT).show();
                    startActivity(back);
                    finish();
                }
                else if(TextUtils.isEmpty(name)){
                    Toast.makeText(AddContactActivity.this, "Isi name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phoneNum)){
                    Toast.makeText(AddContactActivity.this, "Isi nomor telepon",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}