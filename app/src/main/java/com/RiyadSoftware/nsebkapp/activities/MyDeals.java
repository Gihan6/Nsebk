package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.mydeal.MyDealsView;
import com.RiyadSoftware.nsebkapp.Ui.mydeal.Mydealspressenter;
import com.RiyadSoftware.nsebkapp.adapters.DealsAdapter;
import com.RiyadSoftware.nsebkapp.adapters.MyDealsAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.MydealsResponse;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDeals extends BaseActivity implements MyDealsView, DealsAdapter.AddToFavInterface {

    @BindView(R.id.my_deals_recycler)
    RecyclerView my_deals_recycler;
    MyDealsAdapter adapter;


    @BindView(R.id.no_data)
    TextView no_data;

    @Inject
    Mydealspressenter mydealspressenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deals);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        mydealspressenter.attachView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        adapter = new MyDealsAdapter(this);
        my_deals_recycler.setLayoutManager(layoutManager);
//        my_deals_recycler.setAdapter(adapter);

        mydealspressenter.getMydeals(new HomeRequest(new SharedPrefDueDate(this).getUserLogged().getRemember_token()));
    }

    @OnClick(R.id.back_my_deals)
    void back() {
        finish();
    }

    @Override
    public void showData(MydealsResponse response) {

        if (response.getSuccess().equalsIgnoreCase("success")) {
            if (response.getData() == null ||
                    response.getData().size() == 0) {
                no_data.setVisibility(View.VISIBLE);
            } else {
                my_deals_recycler.setAdapter(new DealsAdapter(this, response.getData
                        (), this, true, true));
            }
        } else if (response.getSuccess().equalsIgnoreCase("logged")) {
            logout();
        } else {
            Toast.makeText(this, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void addToFav(String dealID, int position) {

    }
}
