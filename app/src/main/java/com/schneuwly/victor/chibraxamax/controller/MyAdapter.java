package com.schneuwly.victor.chibraxamax.controller;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView adapter
 *
 * @author Victor Schneuwly
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Context ct;
    private List<Pair<Integer, Integer>> historic;

    public MyAdapter(Context ct, List<Pair<Integer, Integer>> historic) {
        this.ct = ct;
        this.historic = Collections.unmodifiableList(historic);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return historic.size();
    }

    public void setHistoric(List<Pair<Integer, Integer>> historic) {
        this.historic = Collections.unmodifiableList(historic);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
