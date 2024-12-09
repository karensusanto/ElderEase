package com.example.elderease;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
    int[][] colors = {
            {0xFFA4F3CE, 0xFF6CE0A9},
            {0xFFE9A3A7, 0xFFE06C73},
            {0xFFEFC1A0, 0xFFF8AD79},
            {0xFFACDBEF, 0xFF76CDF2}
    };
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
        GradientDrawable gradient = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, // Gradient direction
                new int[]{colors[position%4][0], colors[position%4][1]}      // Start and end colors
        );
        gradient.setCornerRadius(16f); // Rounded corners
        holder.contactBtn.setBackgroundTintList(null);
        holder.contactBtn.setBackground(gradient);

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
