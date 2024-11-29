package com.example.elderease;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactRVAdapter extends RecyclerView.Adapter <ContactRVAdapter.ContactHolder>{
    Context context;
    List<Contact> contactList;
    String[] colors = {"#A4F3CE", "#E9A3A7", "#EFC1A0", "#ACDBEF"};
    public ContactRVAdapter(List<Contact> inputContactList){
        contactList = inputContactList;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ContactHolder holder = new ContactHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        String name = contactList.get(position).getName();
        String phoneNum = contactList.get(position).getPhoneNum();
        holder.contactBtn.setText(name);
        holder.contactBtn.setBackgroundColor(Color.parseColor(colors[position%4]));

        holder.contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                Uri phoneUri = Uri.parse("tel:"+phoneNum);
                phoneIntent.setData(phoneUri);
                context.startActivity(phoneIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder{
        Button contactBtn;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            contactBtn = itemView.findViewById(R.id.listItemBtn);
        }
    }
}
