package com.example.chatnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<String> messenges;
    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<String> messenges) {
        this.messenges = messenges;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_loyaut,parent,false);
        return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    String msg = messenges.get(position);
    holder.messege.setText(msg);
    }

    @Override
    public int getItemCount() {
        return messenges.size();
    }
}
