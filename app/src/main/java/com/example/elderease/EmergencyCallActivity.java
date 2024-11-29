package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class EmergencyCallActivity extends AppCompatActivity {
    List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emergency_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dailyWordCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView ecallBackiV = findViewById(R.id.ecallBackIV);
        ImageView ecallEditIV = findViewById(R.id.ecallEditIV);
        Button ecallAddBtn = findViewById(R.id.ecallAddBtn);

        ecallBackiV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(EmergencyCallActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });

        ecallEditIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editContact = new Intent(EmergencyCallActivity.this, EditContactActivity.class);
                startActivity(editContact);
            }
        });

        ecallAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addContact = new Intent(EmergencyCallActivity.this, AddContactActivity.class);
                startActivity(addContact);
            }
        });

//        Intent addContact = getIntent();
//        String newName = addContact.getStringExtra("name");
//        String newPhoneNum = addContact.getStringExtra("phoneNum");

        RecyclerView ecallContactsRV = findViewById(R.id.ecallContactRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ecallContactsRV.setLayoutManager(layoutManager);

        populateContactList();
//        contactList.add(new Contact(newName, newPhoneNum));
        ContactRVAdapter adapter = new ContactRVAdapter(contactList);
        ecallContactsRV.setAdapter(adapter);
    }

    public void populateContactList(){
        contactList = new ArrayList<>();
        contactList.add(new Contact("Andri", "0812345678912"));
        contactList.add(new Contact("Nina", "0898765432198"));
    }
}