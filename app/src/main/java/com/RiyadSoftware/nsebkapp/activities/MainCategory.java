package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Categories.CategoriesPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Categories.CategoriesSubView;
import com.RiyadSoftware.nsebkapp.adapters.MainCategoryAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.CategoriesResponce;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainCategory extends BaseActivity implements CategoriesSubView {

    @BindView(R.id.main_category_recycler)
    RecyclerView main_category_recycler;
    @BindView(R.id.no_data)
    TextView no_data;


    MainCategoryAdapter adapter;

    @Inject
    CategoriesPresenter mCategoriesPresenter;


    //the shared prefrence
    SharedPrefDueDate pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);
        ButterKnife.bind(this);


        pref = new SharedPrefDueDate(this);


        activityComponent().inject(this);
        mCategoriesPresenter.attachView(this);
        //to init the recycler view
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        main_category_recycler.setLayoutManager(layoutManager);


        mCategoriesPresenter.getCatregories(new HomeRequest(pref.getUserLogged().getRemember_token()));
    }


    @OnClick(R.id.back_main_category)
    void back() {
        finish();
    }

    @Override
    public void getCategories(CategoriesResponce categoriesResponce) {
        if (categoriesResponce.getSuccess().equalsIgnoreCase("success")) {
            Log.e("Success", "Success");
            if (categoriesResponce.getData() == null || categoriesResponce.getData().isEmpty())
                no_data.setVisibility(View.VISIBLE);
            else {
                adapter = new MainCategoryAdapter(this, categoriesResponce.getData());
                main_category_recycler.setAdapter(adapter);
            }
        } else {
            Log.e("Success", "Fail");
        }

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
