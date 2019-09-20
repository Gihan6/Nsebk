package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.data.models.ChargeHistoryResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingDateAdapter extends RecyclerView.Adapter<ShippingDateAdapter.ViewHolder> {


    Context context;
    List<ChargeHistoryResponse.Datum> data;

    public ShippingDateAdapter(Context context , List<ChargeHistoryResponse.Datum> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.shipping_date_card,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.package_points.setText(data.get(i).getPoints()+" "+context.getString(R.string.points));
        viewHolder.date.setText(data.get(i).getCreatedAt());
        viewHolder.package_name.setText(data.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.package_name)
        TextView package_name;

        @BindView(R.id.package_points)
        TextView package_points;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
