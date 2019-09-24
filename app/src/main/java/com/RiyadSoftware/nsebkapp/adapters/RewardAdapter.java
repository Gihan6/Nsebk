package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;
import com.RiyadSoftware.nsebkapp.util.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {


    Context context;
    List<RewardsResponse.Datum> data;
    private final OnItemClickListener listener;

    public RewardAdapter(Context context, List<RewardsResponse.Datum> data, OnItemClickListener listener) {
        this.context = context;
        this.data = data;

        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.reward_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int postion) {


        holder.bind(data.get(postion), listener);

        holder.points.setText(data.get(postion).getCoupons());
        holder.endTime.setText(context.getString(R.string.endedin)+" " + data.get(postion).getExpiry_date().split(" ")[0]);
        RequestOptions options = new RequestOptions();
        options.fallback(R.drawable.logo);
        options.placeholder(R.drawable.logo);

        Glide.with(context).load(data.get(postion).getImages()).apply(options).into(holder.image);

        if (data.get(postion).getQuantity()==0){
            holder.rewardBtn.setClickable(false);
        }else {
            holder.rewardBtn.setClickable(true);

        }
        holder.quantityAvailableTxt.setText(""+data.get(postion).getQuantity());

//        holder.rewardBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(context, "comming soon", Toast.LENGTH_LONG).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.pointsTV)
        TextView points;
        @BindView(R.id.endTime)
        TextView endTime;
        @BindView(R.id.rewardIV)
        ImageView image;
        @BindView(R.id.rewardBtn)
        Button rewardBtn;

        @BindView(R.id.text_rewards_available)
        TextView quantityAvailableTxt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(final RewardsResponse.Datum item, final OnItemClickListener listener)
        {
            rewardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }

}
