package com.example.elderease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class EditContactActivity extends AppCompatActivity {

    List<Contact> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dailyWordCL), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView editContactBackBtn = findViewById(R.id.editContactBackBtn);
        editContactBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(EditContactActivity.this, EmergencyCallActivity.class);
                startActivity(back);
                finish();
            }
        });

        RecyclerView editContactRV = findViewById(R.id.editContactRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        editContactRV.setLayoutManager(layoutManager);

        populateContactList();
        EditContactRVAdapter adapter = new EditContactRVAdapter(contactList);
        editContactRV.setAdapter(adapter);
    }

    public void populateContactList(){
        contactList = new ArrayList<>();
        contactList.add(new Contact("Andri", "0812345678912"));
        contactList.add(new Contact("Nina", "0898765432198"));
    }
}