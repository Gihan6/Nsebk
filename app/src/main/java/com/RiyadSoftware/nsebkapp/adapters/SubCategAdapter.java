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

import com.RiyadSoftware.nsebkapp.data.models.CategoriesResponce;
import com.RiyadSoftware.nsebkapp.models.CategoryModel;
import com.bumptech.glide.Glide;
import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.activities.ProductsSubActivity;
import com.RiyadSoftware.nsebkapp.models.SubCategModel;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategAdapter extends RecyclerView.Adapter<SubCategAdapter.ViewHolder> {



    private Context context;
    private List<CategoriesResponce.Datum> data;

    public SubCategAdapter(Context context, List<CategoriesResponce.Datum> data) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.categroy_content_card,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.category_content_card_title.setText(data.get(i).getName());


        RequestOptions options = new RequestOptions();
        options.fallback(R.drawable.logo);
        options.placeholder(R.drawable.logo);

        Glide.with(context).load(data.get(i).getImage()).apply(options).into(viewHolder.category_content_card_img);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ProductsSubActivity.class);
                intent.putExtra("data",data.get(i));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_content_card_title)TextView category_content_card_title;
        @BindView(R.id.category_content_card_img)ImageView category_content_card_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
