package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WinnersAdadper extends RecyclerView.Adapter<WinnersAdadper.ViewHolder> {


    Context context;
    List<String> dataLiat;

    public WinnersAdadper(Context context, List<String> dataLiat) {
        this.context = context;
        this.dataLiat = dataLiat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.winner_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.winner_name.setText(dataLiat.get(i));


    }

    @Override
    public int getItemCount() {
        return dataLiat == null ? 0 : dataLiat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.winner_name)
        TextView winner_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
