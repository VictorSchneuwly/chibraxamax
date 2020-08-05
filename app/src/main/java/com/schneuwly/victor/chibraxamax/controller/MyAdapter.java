package com.schneuwly.victor.chibraxamax.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.schneuwly.victor.chibraxamax.R;
import com.schneuwly.victor.chibraxamax.model.Game;
import com.schneuwly.victor.chibraxamax.model.Historic;

/**
 * RecyclerView adapter
 *
 * @author Victor Schneuwly
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static View.OnClickListener listener;


    private final Context context;
    private final Historic<Game.HistoricEntry> historic;

    public MyAdapter(Context context, Historic<Game.HistoricEntry> historic, View.OnClickListener listener) {
        this.context = context;
        this.historic = historic;
        MyAdapter.listener = listener;
    }

    public MyAdapter(Context context, Historic<Game.HistoricEntry> historic) {
        this(context, historic, listener);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyViewHolder(inflater.inflate(R.layout.statistic_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!(historic.isEmpty())) {
            int maxPosition = getItemCount() - 1;
            int displayId = maxPosition - position;

            holder.team0_score.setText(String.valueOf(historic.get(displayId).first()));
            holder.team1_score.setText(String.valueOf(historic.get(displayId).second()));

            if (position == 0) {
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(listener);
            } else {
                holder.delete.setVisibility(View.INVISIBLE);
            }

            holder.topLine.setVisibility(
                    (position != 0) ? View.VISIBLE : View.INVISIBLE
            );

            holder.endLine.setVisibility(
                    (position == maxPosition) ? View.VISIBLE : View.INVISIBLE
            );
        }

    }

    @Override
    public int getItemCount() {
        return historic.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView delete, team0_score, team1_score;
        View topLine, endLine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            delete = itemView.findViewById(R.id.row_delete);
            team0_score = itemView.findViewById(R.id.row_team0_score);
            team1_score = itemView.findViewById(R.id.row_team1_score);
            topLine = itemView.findViewById(R.id.row_top_line);
            endLine = itemView.findViewById(R.id.row_end_line);
        }
    }
}
