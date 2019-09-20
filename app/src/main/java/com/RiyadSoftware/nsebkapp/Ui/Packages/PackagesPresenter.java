package com.RiyadSoftware.nsebkapp.Ui.Packages;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.ChargeRequest;
import com.RiyadSoftware.nsebkapp.data.models.ChargeResponse;
import com.RiyadSoftware.nsebkapp.data.models.HomeModel;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.PackageResponse;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;
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
public class PackagesPresenter extends BasePresenter<PackagesSubView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public PackagesPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(PackagesSubView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getOffers(final HomeRequest homeRequest) {
        getMvpView().showLoader();
        mDataManager.getOffers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<OffersResponseModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull OffersResponseModel offersResponseModel) {

                        getMvpView().hideLoader();
                        getMvpView().getOffers(offersResponseModel);

                        getPackages(homeRequest);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                        getPackages(homeRequest);
                    }

                    @Override
                    public void onComplete() {

                        getMvpView().hideLoader();
                    }
                });
    }

    public void getPackages(HomeRequest homeRequest) {
        getMvpView().showLoader();
        mDataManager.getPackages(homeRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PackageResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull PackageResponse packageResponse) {

                        getMvpView().hideLoader();
                        getMvpView().getPackages(packageResponse);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().hideLoader();

                    }
                });
    }
    public void Charge(ChargeRequest chargeRequest) {
        getMvpView().showLoader();
        mDataManager.Charge(chargeRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ChargeResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull ChargeResponse packageResponse) {
                        getMvpView().hideLoader();
                        getMvpView().afterBuy(packageResponse);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().hideLoader();

                    }
                });
    }



    public void getCouponResultPresenter(String token , String code ,int package_id) {
        CouponRequest couponRequest = new CouponRequest();

        couponRequest.setCoupon_code(code);
        couponRequest.setToken(token);
        couponRequest.setPackage_id(package_id);

        getMvpView().showLoader();
        try{
        mDataManager.getCouponResult(couponRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CouponResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull CouponResponse couponResponse) {

                        getMvpView().getCouponResult(couponResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showError();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                        getMvpView().hideLoader();
                    }
                });

        }catch (Exception e)
        {

            getMvpView().hideLoader();
            System.out.println(e.getMessage());

        }
    }




}
