package com.RiyadSoftware.nsebkapp.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyAndConditionAdapter extends RecyclerView.Adapter<PrivacyAndConditionAdapter.ViewHolder> {


    Context context;
    List<TermsResponse.Datum> datumList;

    public PrivacyAndConditionAdapter(Context context, List<TermsResponse.Datum> datumList) {
        this.context = context;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.privacy_condition_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.privacy_condition_item_textview.setText(datumList.get(i).getDisc());
    }

    @Override
    public int getItemCount() {
        return datumList == null ? 0 : datumList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.privacy_condition_item_textview)
        TextView privacy_condition_item_textview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
