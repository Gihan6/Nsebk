package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsMvpView;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsResponse;
import com.RiyadSoftware.nsebkapp.adapters.PrivacyAndConditionAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Conditions extends BaseActivity implements TermsMvpView {
    @BindView(R.id.condition_recycler)
    RecyclerView condition_recycler;
    PrivacyAndConditionAdapter adapter;

    @Inject
    TermsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
        ButterKnife.bind(this);

        activityComponent().inject(this);
        mPresenter.attachView(this);

        mPresenter.getterms();
    }

    @OnClick(R.id.back_condition)
    void back() {
        finish();
    }

    @Override
    public void showTerms(TermsResponse offersResponseModel) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new PrivacyAndConditionAdapter(this, offersResponseModel.getData());
        condition_recycler.setLayoutManager(layoutManager);
        condition_recycler.setAdapter(adapter);

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
