package com.RiyadSoftware.nsebkapp.Ui.Packages;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.ChargeResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponResponse;

import java.util.List;

public interface PackagesSubView
        extends MvpView {

    public void getOffers(OffersResponseModel offersResponseModel);
    public void getPackages(PackageResponse packageResponse);
    public void afterBuy(ChargeResponse packageResponse);

    public void getCouponResult(CouponResponse couponResponse);

}
