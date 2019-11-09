package com.example.chatnew;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView messege;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        messege = itemView.findViewById(R.id.message_item);
    }
}
