package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsMvpView;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsResponse;
import com.RiyadSoftware.nsebkapp.adapters.AboutAppAdadper;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUs extends BaseActivity implements TermsMvpView {

    @BindView(R.id.about_recyclerView)
    RecyclerView aboutRecyclerView;

    @Inject
    TermsPresenter termsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        termsPresenter.attachView(this);
        termsPresenter.getAbout();
    }

    @OnClick(R.id.back_my_interest)
    void back_buy() {
        finish();
    }

    @Override
    public void showTerms(TermsResponse offersResponseModel) {
        aboutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        aboutRecyclerView.setNestedScrollingEnabled(false);
        aboutRecyclerView.setHasFixedSize(true);
        aboutRecyclerView.setAdapter(new AboutAppAdadper(this, offersResponseModel.getData()));


    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
