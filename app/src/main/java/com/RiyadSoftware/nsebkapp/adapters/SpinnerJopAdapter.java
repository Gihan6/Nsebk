package com.RiyadSoftware.nsebkapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.models.JopModel;

import java.util.List;

public class SpinnerJopAdapter extends BaseAdapter {

    Context context;
    List<JopModel> data;
    LayoutInflater inflter;

    public SpinnerJopAdapter(Context applicationContext, List<JopModel> data) {
        this.context = applicationContext;
        this.data = data;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.single_layout_spinner, null);
        TextView branchName = (TextView) view.findViewById(R.id.tv_singleLayoutSpinner_branchName);
        branchName.setText(data.get(i).getName());
        return view;
    }
}
