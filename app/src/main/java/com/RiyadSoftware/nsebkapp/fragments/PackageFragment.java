package com.RiyadSoftware.nsebkapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.RiyadSoftware.nsebkapp.R;
import com.RiyadSoftware.nsebkapp.Ui.Packages.PackagesPresenter;
import com.RiyadSoftware.nsebkapp.Ui.Packages.PackagesSubView;
import com.RiyadSoftware.nsebkapp.adapters.OffersAdapter;
import com.RiyadSoftware.nsebkapp.adapters.ShippingCardsAdapter;
import com.RiyadSoftware.nsebkapp.base.BaseFragment;
import com.RiyadSoftware.nsebkapp.data.models.ChargeRequest;
import com.RiyadSoftware.nsebkapp.data.models.ChargeResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponResponse;
import com.RiyadSoftware.nsebkapp.models.PackageModel;
import com.RiyadSoftware.nsebkapp.util.SharedPrefDueDate;
import com.RiyadSoftware.nsebkapp.view.CircleIndicator2;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackageFragment extends BaseFragment implements PackagesSubView, ShippingCardsAdapter.PackagesInterface {

    @BindView(R.id.shipping_cards_recycler)
    RecyclerView shipping_cards_recycler;
    ShippingCardsAdapter adapterPackage;

    OffersAdapter adapterOffer;
    ArrayList<String> dataOffers = new ArrayList<>();

    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.imagesRV)
    RecyclerView imagesRV;
    SharedPrefDueDate sharedPrefDueDate;

    @Inject
    PackagesPresenter mPackagesPresenter;

    //the data of the pakages
    ArrayList<PackageModel> dataPackage = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_shipping_cards, container, false);
        ButterKnife.bind(this, root);


        return root;
    }

    @Override
    public void showLoader() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activityComponent().inject(this);
        mPackagesPresenter.attachView(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), 2);
        shipping_cards_recycler.setLayoutManager(layoutManager);


        //init the offers

        RecyclerView.LayoutManager
                layoutManager6 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        imagesRV.setLayoutManager(layoutManager6);

        sharedPrefDueDate = new SharedPrefDueDate(getActivity());
        mPackagesPresenter.getPackages(new HomeRequest(sharedPrefDueDate.getUserLogged().getRemember_token()));


        if(sharedPrefDueDate.getUserLogged().getRemember_token()!= null)
        mPackagesPresenter.getOffers(new HomeRequest(sharedPrefDueDate.getUserLogged().getRemember_token()));
    }


    @Override
    public void getOffers(OffersResponseModel offersResponseModel) {
        if (offersResponseModel.getSuccess().equalsIgnoreCase("success")) {
            adapterOffer = new OffersAdapter(offersResponseModel.getData(), getContext());
            imagesRV.setAdapter(adapterOffer);

            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
            pagerSnapHelper.attachToRecyclerView(imagesRV);

            if (getView() == null)
                return;
            CircleIndicator2 indicator = getView().findViewById(R.id.indicator);
            indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
            adapterOffer.registerAdapterDataObserver(indicator.getAdapterDataObserver());
        }
    }

    @Override
    public void getPackages(PackageResponse packageResponse) {
        adapterPackage = new ShippingCardsAdapter(getContext(), packageResponse.getData(), this);
        shipping_cards_recycler.setAdapter(adapterPackage);
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.buy_dialog);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(dialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog.show();
//        dialog.getWindow().setAttributes(lp);
// retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

// inflate and adjust layout
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.buy_dialog, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 0.7f));
//        layout.setMinimumHeight((int)(displayRectangle.height() * 0.7f));
        dialog.setContentView(layout);
        TextView dialogButton = (TextView) dialog.findViewById(R.id.cancel_tv);
        TextView ok_tv = (TextView) dialog.findViewById(R.id.ok_tv);
        TextView dialog_txt = (TextView) dialog.findViewById(R.id.dialog_txt);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog_txt.setText(getString(R.string.part_done));

        dialog.show();
    }


    @Override
    public void afterBuy(ChargeResponse packageResponse) {
        if (packageResponse.getSuccess().equalsIgnoreCase("success")) {
//            SharedPrefDueDate pref = new SharedPrefDueDate(getActivity());
//            pref.setUserPointsFromPk();
//            pref.setUserCouponsFromPk();
            showDialog();
        } else {
            Toast.makeText(getActivity(), "" + packageResponse.getErrors().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCouponResult(CouponResponse couponResponse) {

    }


    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void buyPackage(int id) {
//        "token": "MP5cKbh7wYoDOIwX0zM71J8dSLOzF54ch5MDi0YLM22Fcl3BFZaq3X1tVk4d",
//                "package_id": 6
        mPackagesPresenter.Charge(new ChargeRequest("MP5cKbh7wYoDOIwX0zM71J8dSLOzF54ch5MDi0YLM22Fcl3BFZaq3X1tVk4d",
                6));
    }
}
