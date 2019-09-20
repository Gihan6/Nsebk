package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Inteerst.InterestPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Inteerst.InterestResponseModel;
import com.RiyadSoftware.nsebkapp.Ui.Inteerst.InterestView;
import com.RiyadSoftware.nsebkapp.Ui.Inteerst.SaveInterestRequest;
import com.RiyadSoftware.nsebkapp.adapters.MyInterestAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyInterests extends BaseActivity implements InterestView {


    @BindView(R.id.interest_recycler)
    RecyclerView interest_recycler;
    MyInterestAdapter adapter;
    @Inject
    InterestPresenter mInterestPresenter;
    InterestResponseModel mResponseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_interests);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        mInterestPresenter.attachView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        interest_recycler.setLayoutManager(layoutManager);

        mInterestPresenter.getinterst(new HomeRequest(new SharedPrefDueDate(this)
                .getUserLogged().getRemember_token()));
    }

    @OnClick(R.id.back_my_interest)
    void back() {
        finish();
    }

    @OnClick(R.id.my_interest_save_button)
    void my_interest_save_button() {
        if (mResponseModel == null)
            return;

        List<String> list = new ArrayList<>();
        for (InterestResponseModel.Datum data : mResponseModel.getData()) {
            if (!data.getIs_interest().equalsIgnoreCase("no")) {
                list.add(String.valueOf(data.getId()));
            }
        }

        if (list.isEmpty()) {
            Toast.makeText(this, getString(R.string.select_inter), Toast.LENGTH_SHORT).show();
            return;
        }
        mInterestPresenter.SaveInterest(new SaveInterestRequest(new SharedPrefDueDate(this)
                .getUserLogged().getRemember_token(), list
        ));
    }

    @Override
    public void showInterest(InterestResponseModel response) {
        mResponseModel = response;

        if (response.getData() != null)
            adapter = new MyInterestAdapter(this, response.getData());
        interest_recycler.setAdapter(adapter);

    }

    @Override
    public void showErrorInLogin() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
