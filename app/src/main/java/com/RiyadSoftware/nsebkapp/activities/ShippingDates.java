package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.ChargesHisatory.ChargesPresenter;
import com.RiyadSoftware.nsebkapp.Ui.ChargesHisatory.CheargesMvpView;
import com.RiyadSoftware.nsebkapp.adapters.ShippingDateAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.ChargeHistoryResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShippingDates extends BaseActivity implements CheargesMvpView {

    @BindView(R.id.shipping_date_recycler)
    RecyclerView shipping_date_recycler;
    @BindView(R.id.no_data)
    TextView no_data;
    ShippingDateAdapter adapter;

    @Inject
    ChargesPresenter mChargesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_dates);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        mChargesPresenter.attachView(this);
        mChargesPresenter.getChargeHistory(new HomeRequest(new SharedPrefDueDate(this).getUserLogged().getRemember_token()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        shipping_date_recycler.setLayoutManager(layoutManager);

    }

    @OnClick(R.id.back_shipping_date)
    void back() {
        finish();
    }

    @Override
    public void showListOfCharges(ChargeHistoryResponse chargeResponse) {
        if (chargeResponse.getSuccess().equalsIgnoreCase("success")) {
            if (chargeResponse.getData() == null) {
                no_data.setVisibility(View.VISIBLE);
            } else {
                adapter = new ShippingDateAdapter(this, chargeResponse.getData());
                shipping_date_recycler.setAdapter(adapter);
            }
        }

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
