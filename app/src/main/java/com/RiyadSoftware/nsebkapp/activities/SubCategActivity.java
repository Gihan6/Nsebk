package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Categories.CategoriesPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Categories.CategoriesSubView;
import com.RiyadSoftware.nsebkapp.adapters.SubCategAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.CategoriesResponce;
import com.RiyadSoftware.nsebkapp.data.models.SubCatRequest;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubCategActivity extends BaseActivity implements CategoriesSubView {


    //init the views

    @BindView(R.id.subcategRV)
    RecyclerView subcategRV;
    @BindView(R.id.titleTV)
    TextView titleTV;
    @BindView(R.id.subTitleTV)
    TextView subTitleTV;
    @BindView(R.id.no_data)
    TextView no_data;

    //the adapter and the recycler data
    SubCategAdapter adapter;


    @Inject
    CategoriesPresenter mCategoriesPresenter;

    //shared prefrence
    SharedPrefDueDate pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcateg);
        ButterKnife.bind(this);


        pref = new SharedPrefDueDate(this);

        CategoriesResponce.Datum dataModel = (CategoriesResponce.Datum) getIntent().getSerializableExtra("data");

        if (dataModel == null)
            return;


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        subcategRV.setLayoutManager(layoutManager);
        subcategRV.setAdapter(adapter);
        titleTV.setText(dataModel.getName());
        subTitleTV.setText(getString(R.string.type_sub) + " " + dataModel.getName());


        activityComponent().inject(this);
        mCategoriesPresenter.attachView(this);

        mCategoriesPresenter.getSubCategories(new SubCatRequest(dataModel.getId(),
                pref.getUserLogged().getRemember_token()));

    }


    @OnClick(R.id.back_category_contnet)
    void back() {
        finish();
    }

    @Override
    public void getCategories(CategoriesResponce categoriesResponce) {
        if (categoriesResponce.getSuccess().equalsIgnoreCase("success")) {
            if (categoriesResponce.getData() == null || categoriesResponce.getData().isEmpty())
                no_data.setVisibility(View.VISIBLE);
            else {
                subcategRV.setAdapter(new SubCategAdapter(this, categoriesResponce.getData()));
            }
        } else {
            showEmpty();
        }

    }

    @Override
    public void showEmpty() {
        no_data.setVisibility(View.VISIBLE);
        subcategRV.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }
}
