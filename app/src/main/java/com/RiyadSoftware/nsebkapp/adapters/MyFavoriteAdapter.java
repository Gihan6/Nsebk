package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.data.models.MyFavResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.ViewHolder> {


    private Context context;
    private List<MyFavResponse.Datum> data;

    private SharedPrefDueDate pref;
    FavActions favActions;

    public void setFavActions(FavActions favActions) {
        this.favActions = favActions;
    }

    public MyFavoriteAdapter(Context context, List<MyFavResponse.Datum> data) {
        this.context = context;
        this.data = data;
        pref = new SharedPrefDueDate(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.my_favorite_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int postion) {


        if (data.get(postion).getExpiry_date() != null)
            if (StringToData(data.get(postion).getExpiry_date().split(" ")[0]) != null)
                if (StringToData(data.get(postion).getExpiry_date().split(" ")[0]).getTime() == currentDate().getTime()) {
                    holder.statusTV.setText("صفقة قائمة");
                    holder.statusTV.setTextColor(context.getResources().getColor(R.color.green));
                    Log.e("//////", "صفقة قائمة");
                } else if (StringToData(data.get(postion).getExpiry_date().split(" ")[0]).getTime() > currentDate().getTime()) {
                    holder.statusTV.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    holder.statusTV.setText("صفقة لاحقة");
                    Log.e("//////", "صفقة لاحقة");
                } else if (StringToData(data.get(postion).getExpiry_date().split(" ")[0]).getTime() < currentDate().getTime()) {
                    holder.statusTV.setTextColor(context.getResources().getColor(R.color.login_google));
                    holder.statusTV.setText("صفقة منتهية");
                    Log.e("//////", "صفقة منتهية");
                } else
                    Log.e("//////", "Shit");

        holder.titleTV.setText(data.get(postion).getTitle());
//


//            holder.statusTV.setText(model.getStatus());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favActions!=null)
                    favActions.deleteFav(postion , data.get(postion).getDeal_id());
            }
        });


        if (context != null) {
            RequestOptions options = new RequestOptions();
            options.fallback(R.drawable.logo);
            options.placeholder(R.drawable.logo);
            Glide.with(context).load(data.get(postion).getImage()).apply(options).into(holder.image);
        }
    }

    public interface FavActions{
        void deleteFav(int position,int delaID);
    }
    private Date currentDate() {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);
        return StringToData(todayString);
    }

    private Date StringToData(String dtStart) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dtStart);
            System.out.println(date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return data==null?0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.productIV)
        ImageView image;
        @BindView(R.id.deleteIV)
        ImageView delete;
        @BindView(R.id.likeIV)
        ImageView like;
        @BindView(R.id.statusTV)
        TextView statusTV;
        @BindView(R.id.titleTV)
        TextView titleTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
