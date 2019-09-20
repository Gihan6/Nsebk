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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDealsAdapter extends RecyclerView.Adapter<MyDealsAdapter.ViewHolder> {


    Context context;

    String [] dates = {"4/7/2019","8/3/2018","5/5/2018","9/3/2017","7/2/2016"};
    String [] titles = {"عطر Boss","سيارة Bwm","ملابس شتاء","بوردة","شقة 4 غرف"};
    int [] images = {R.drawable.mc11,R.drawable.mc21,R.drawable.mc31,R.drawable.mc41,R.drawable.mc52};

    public MyDealsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.my_deals_card,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.my_deals_card_date.setText(dates[i]);
        viewHolder.my_deals_card_title.setText(titles[i]);
        viewHolder.my_deals_card_img.setImageDrawable(context.getResources().getDrawable(images[i]));

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.my_deals_card_title)TextView my_deals_card_title;
        @BindView(R.id.my_deals_card_date)TextView my_deals_card_date;
        @BindView(R.id.my_deals_card_img)ImageView my_deals_card_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
