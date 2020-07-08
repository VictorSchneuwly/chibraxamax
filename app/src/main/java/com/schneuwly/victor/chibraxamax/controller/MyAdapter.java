package com.schneuwly.victor.chibraxamax.controller;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Game;

import java.util.Collections;
import java.util.List;

/**
 * RecyclerView adapter
 *
 * @author Victor Schneuwly
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final Context context;
    private List<Pair<Integer, Integer>> historic;
    private Game game;

    public MyAdapter(Context context, Game game) {
        this.context = context;
        this.game = game;
        this.historic = Collections.unmodifiableList(game.getHistoric());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyViewHolder(inflater.inflate(R.layout.historic_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int displayId = getItemCount() - position;

        holder.team0_score.setText(historic.get(displayId).first);
        holder.team1_score.setText(historic.get(displayId).second);

        if (position == 0) {
            holder.delete.setVisibility(View.VISIBLE);
            holder.delete.setOnClickListener(l ->{
                game.undoLastMove();
                setHistoric(game.getHistoric());
            });
        } else {
            holder.delete.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return historic.size();
    }

    public void setHistoric(List<Pair<Integer, Integer>> historic) {
        this.historic = Collections.unmodifiableList(historic);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView delete, team0_score, team1_score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            delete = itemView.findViewById(R.id.row_delete);
            team0_score = itemView.findViewById(R.id.row_team0_score);
            team1_score = itemView.findViewById(R.id.row_team1_score);
        }
    }
}
