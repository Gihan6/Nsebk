package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.activities.Shipping_buy;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.RiyadSoftware.nsebkapp.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingCardsAdapter extends RecyclerView.Adapter<ShippingCardsAdapter.ViewHolder> {


    Context context;
    List<PackageResponse.Datum> data;
    PackagesInterface packagesInterface;

    public ShippingCardsAdapter(Context context, List<PackageResponse.Datum> data, PackagesInterface packagesInterface) {
        this.context = context;
        this.data = data;
        this.packagesInterface = packagesInterface;
    }

    public interface PackagesInterface {
        public void buyPackage(int id);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.shipping_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int postion) {


        SharedPrefDueDate     pref = new SharedPrefDueDate(context);

        holder.points.setText(data.get(postion).getPoints());
        holder.priceTV.setText(data.get(postion).getCost());
        holder.tv_title.setText(data.get(postion).getTitle());
        holder.tv_ticket.setText(data.get(postion).getCoupons());

        if(pref.getCurrency().equals("2"))
            holder.text_currency_package.setText(context.getString(R.string.sar));
        else
            holder.text_currency_package.setText(context.getString(R.string.dollar_currency));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Shipping_buy.class).putExtra("data", data.get(postion)));
            }
        });

        if ((postion + 1) % 2 == 0) {
            holder.right_sep.setVisibility(View.INVISIBLE);
            holder.left_sep.setVisibility(View.VISIBLE);
        } else {
            holder.right_sep.setVisibility(View.VISIBLE);
            holder.left_sep.setVisibility(View.INVISIBLE);
        }

        holder.buy_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // packagesInterface.buyPackage(data.get(postion).getId());
                context.startActivity(new Intent(context, Shipping_buy.class).putExtra("data", data.get(postion)));

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.pointsTV)
        TextView points;
        @BindView(R.id.priceTV)
        TextView priceTV;
        @BindView(R.id.left_sep)
        TextView left_sep;
        @BindView(R.id.right_sep)
        TextView right_sep;
        @BindView(R.id.bottom_sep)
        TextView bottom_sep;
        @BindView(R.id.main_deals_card_date)
        TextView buy_package;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_ticket)
        TextView tv_ticket;

        @BindView(R.id.text_currency_package)
        TextView text_currency_package;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
