package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Inteerst.InterestResponseModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInterestAdapter extends RecyclerView.Adapter<MyInterestAdapter.ViewHolder> {


    Context context;
    List<InterestResponseModel.Datum> data;

    public MyInterestAdapter(Context context, List<InterestResponseModel.Datum> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.interest_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.interest_card_checkbox.setText(data.get(i).getTitle());
        viewHolder.interest_card_checkbox.setOnCheckedChangeListener(null);

        Log.e("vdcssdff",data.get(i).getIs_interest());
        if (data.get(i).getIs_interest().trim() .contains("yes")){
            viewHolder.interest_card_checkbox.setChecked(true);
        }else {
            viewHolder.interest_card_checkbox.setChecked(false);
        }

        viewHolder.interest_card_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                  data.get(i).setIs_interest("yes");
                }else {
                    data.get(i).setIs_interest("no");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.interest_card_checkbox)
        CheckBox interest_card_checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
