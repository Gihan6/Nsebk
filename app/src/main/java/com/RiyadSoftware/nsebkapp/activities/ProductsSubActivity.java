package com.RiyadSoftware.nsebkapp.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.ProductActivity.ProductSubView;
import com.RiyadSoftware.nsebkapp.Ui.ProductActivity.ProductsPresenter;
import com.RiyadSoftware.nsebkapp.adapters.DealsAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseActivity;
import com.RiyadSoftware.nsebkapp.data.models.AddFavRequest;
import com.RiyadSoftware.nsebkapp.data.models.AddFavResponse;
import com.RiyadSoftware.nsebkapp.data.models.CategoriesResponce;
import com.RiyadSoftware.nsebkapp.data.models.CategoryProductResponse;
import com.RiyadSoftware.nsebkapp.data.models.CategoryProductsRequest;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductsSubActivity extends BaseActivity implements ProductSubView, DealsAdapter.AddToFavInterface {

    //init the view
    @BindView(R.id.titleTV)
    TextView titleTV;
    @BindView(R.id.no_data)
    TextView no_data;
    @BindView(R.id.productRV)
    RecyclerView productRV;

    int mPosition;

    List<HomeModel.DealModel> mList;

    //init the adapter and the data
    DealsAdapter adapter;


    CategoriesResponce.Datum intentData;

    //init the shared prefrence
    SharedPrefDueDate pref;

    @Inject
    ProductsPresenter mpProductsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_sub);
        ButterKnife.bind(this);


        activityComponent().inject(this);
        mpProductsPresenter.attachView(this);
        pref = new SharedPrefDueDate(this);
        intentData = (CategoriesResponce.Datum) getIntent().getSerializableExtra("data");

        if (intentData == null)
            return;

        titleTV.setText(intentData.getName());

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        productRV.setLayoutManager(layoutManager);


        getProducts();
    }


    private void getProducts() {


        mpProductsPresenter.getProducts(new CategoryProductsRequest(pref.getUserLogged().getRemember_token(),
                intentData.getId()));
    }

    @OnClick(R.id.back)
    void backAction() {
        finish();
    }


    @Override
    public void showData(CategoryProductResponse offersResponseModel) {
        if (offersResponseModel.getSuccess().equalsIgnoreCase("success")) {
            if (offersResponseModel.getData().getDealss() == null ||
                    offersResponseModel.getData().getDealss().size() == 0) {
                no_data.setVisibility(View.VISIBLE);
            } else {
                mList = offersResponseModel.getData().getDealss();
                adapter = new DealsAdapter(this, mList, this, true);
            }

            productRV.setAdapter(adapter);
        } else if (offersResponseModel.getSuccess().equalsIgnoreCase("logged")) {
            logout();
        } else {
            Toast.makeText(this, getString(R.string.error_in_data), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void DealAddedToFav(AddFavResponse addFavResponse) {
        if (addFavResponse.getSuccess().equalsIgnoreCase("success")) {
            mList.get(mPosition).setIs_favorite(!mList.get(mPosition).isIs_favorite());
            adapter.notifyDataSetChanged();

        } else if (addFavResponse.getSuccess().equalsIgnoreCase("logged")) {
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
        mPosition = position;
        mpProductsPresenter.addToFav(new AddFavRequest(pref.getUserLogged().getRemember_token(), dealID));
    }
}
