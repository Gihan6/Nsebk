package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.activities.Buy;
import com.RiyadSoftware.nsebkapp.activities.FinishDeal;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder> {


    private Context context;
    //the data
    private List<HomeModel.DealModel> data;
    private SharedPrefDueDate pref;
    boolean mIsLinear;
    AddToFavInterface addToFavInterface;
    boolean hideLike;
    boolean isCommingSoon;

    public DealsAdapter(Context context, List<HomeModel.DealModel> data, AddToFavInterface addToFavInterface, boolean isLinear) {
        this.context = context;
        this.data = data;

        this.mIsLinear = isLinear;
        this.addToFavInterface = addToFavInterface;
        pref = new SharedPrefDueDate(context);
    }

    public DealsAdapter(Context context, boolean isCommingSoon, List<HomeModel.DealModel> data, AddToFavInterface addToFavInterface, boolean isLinear) {
        this.context = context;
        this.data = data;
        this.isCommingSoon = isCommingSoon;

        this.mIsLinear = isLinear;
        this.addToFavInterface = addToFavInterface;
        pref = new SharedPrefDueDate(context);
    }

    public DealsAdapter(Context context, List<HomeModel.DealModel> data,
                        AddToFavInterface addToFavInterface, boolean isLinear, boolean hideLike) {
        this.context = context;
        this.data = data;

        this.mIsLinear = isLinear;
        this.hideLike = hideLike;
        this.addToFavInterface = addToFavInterface;
        pref = new SharedPrefDueDate(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.deals_item, viewGroup, false);
        if (mIsLinear) {
            RecyclerView.LayoutParams lp;
            lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int postion) {


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fallback(R.drawable.logo);
        requestOptions.placeholder(R.drawable.logo);
        Glide.with(context).load(data.get(postion).getImage()).apply(requestOptions).into(holder.productIV);

        if (hideLike)
            holder.likeIV.setVisibility(View.INVISIBLE);


        holder.titleTV.setText(data.get(postion).getTitle());

        holder.product_price.setText(data.get(postion).getInitial_price() + " " + context.getString(R.string.sar));

        holder.points_tv.setText(data.get(postion).getPoints() + " " + context.getString(R.string.tickets));
        holder.points_lbl.setText((data.get(postion).getPoints()+""));


//        holder.points_tv.setText(data.get(postion).getPoints() + " " + context.getString(R.string.points));
        if (isCommingSoon) {
            holder.time_to_end.setText(context.getString(R.string.comming_soon));
        } else {
            if(data.get(postion).getExpiry_date()!= null){
            String date = data.get(postion).getExpiry_date().substring(0,data.get(postion).getExpiry_date().indexOf(":")-2);
            String counter = data.get(postion).getExpiry_date().substring(data.get(postion).getExpiry_date().indexOf(":")-2 ,data.get(postion).getExpiry_date().length());

                holder.time_to_end.setText(context.getString(R.string.finish));
        }
        }
        //holder.points_lbl.setText("+" + data.get(postion).getTender_cost() == null ? "0" : data.get(postion).getTender_cost());
//
//        if(data.get(postion).getTender_coupon() == null)
//            holder.points_lbl.setText("0");
//        else
//        {
//            holder.points_lbl.setText(data.get(postion).getTender_coupon());
//        }
        if (data.get(postion).isIs_favorite()) {
            holder.likeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.cards_heart));
        } else {
            holder.likeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.heart_outline));

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FinishDeal.class);
                intent.putExtra("data", data.get(postion).getDeal_id());
                intent.putExtra("points", data.get(postion).getPoints());
                context.startActivity(intent);
            }
        });


        holder.likeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addToFavInterface != null)
                    addToFavInterface.addToFav(String.valueOf(data.get(postion).getDeal_id()), postion);
//                Toast.makeText(context,"addded like ",Toast.LENGTH_LONG).show();
//                sendDataTOServer(postion);
//                holder.likeIV.setImageDrawable(context.getResources().getDrawable(R.drawable.cards_heart));
            }
        });


    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        //        @BindView(R.id.productParent)LinearLayout parent;
//        @BindView(R.id.priceTV)TextView priceTV;
//        @BindView(R.id.pointsTV)TextView pointsTV;
        @BindView(R.id.productIV)
        ImageView productIV;
        @BindView(R.id.product_name)
        TextView titleTV;
        @BindView(R.id.time_to_end)
        TextView time_to_end;
        @BindView(R.id.points_tv)
        TextView points_tv;
        @BindView(R.id.product_price)
        TextView product_price;
        @BindView(R.id.points_lbl)
        TextView points_lbl;
        //        @BindView(R.id.timeTV)TextView timeTV;
        @BindView(R.id.likeIV)
        ImageView likeIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * here to send  the data from the server
     */

    public interface AddToFavInterface {
        void addToFav(String dealID, int position);
    }
}
