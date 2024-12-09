package com.example.elderease;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EditContactRVAdapter extends RecyclerView.Adapter <EditContactRVAdapter.EditContactHolder>{
    Context context;
    List<Contact> contactList;
    int[][] colors = {
            {0xFFA4F3CE, 0xFF6CE0A9},
            {0xFFE9A3A7, 0xFFE06C73},
            {0xFFEFC1A0, 0xFFF8AD79},
            {0xFFACDBEF, 0xFF76CDF2}
    };
    public class EditContactHolder extends RecyclerView.ViewHolder{
        Button contactBtn;
        ImageButton deleteBtn;
        public EditContactHolder(@NonNull View itemView) {
            super(itemView);
            contactBtn = itemView.findViewById(R.id.editListItemNameBtn);
            deleteBtn = itemView.findViewById(R.id.editListItemDelBtn);
        }
    }
    public EditContactRVAdapter(List<Contact>inputContactList){
        contactList = inputContactList;
    }
    @NonNull
    @Override
    public EditContactRVAdapter.EditContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.edit_list_item, parent, false);
        EditContactRVAdapter.EditContactHolder holder = new EditContactRVAdapter.EditContactHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditContactRVAdapter.EditContactHolder holder, int position) {
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


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //apus dr db
                Toast.makeText(context, name+" deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
