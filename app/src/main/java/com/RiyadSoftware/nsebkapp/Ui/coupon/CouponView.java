package com.RiyadSoftware.nsebkapp.Ui.coupon;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.ChargeResponse;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponResponse;

public interface CouponView
        extends MvpView {

    public void getCouponResult(CouponResponse couponResponse);

}
