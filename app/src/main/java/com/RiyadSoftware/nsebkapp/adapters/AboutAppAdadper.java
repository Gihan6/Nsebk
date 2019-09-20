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

public class AboutAppAdadper extends RecyclerView.Adapter<AboutAppAdadper.ViewHolder> {


    Context context;
    List<TermsResponse.Datum> dataLiat;

    public AboutAppAdadper(Context context, List<TermsResponse.Datum> dataLiat) {
        this.context = context;
        this.dataLiat = dataLiat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.about_app_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(dataLiat.get(i).getTitle());
        viewHolder.desc.setText(dataLiat.get(i).getDisc());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.desc.getVisibility() == View.VISIBLE) {
                    viewHolder.desc.setVisibility(View.GONE);
                    viewHolder.arrow.setRotation(0f);
                } else {
                    viewHolder.desc.setVisibility(View.VISIBLE);
                    viewHolder.arrow.setRotation(180f);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataLiat == null ? 0 : dataLiat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.arrow)
        ImageView arrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
