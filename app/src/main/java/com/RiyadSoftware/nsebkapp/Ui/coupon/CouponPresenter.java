package com.RiyadSoftware.nsebkapp.Ui.coupon;

import com.RiyadSoftware.nsebkapp.Ui.Packages.PackagesSubView;
import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.ChargeRequest;
import com.RiyadSoftware.nsebkapp.data.models.ChargeResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponRequest;
import com.RiyadSoftware.nsebkapp.data.models.coupon.CouponResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class CouponPresenter extends BasePresenter<CouponView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public CouponPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CouponView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


}
