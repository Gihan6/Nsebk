package com.RiyadSoftware.nsebkapp.Ui.Inteerst;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class InterestPresenter extends BasePresenter<InterestView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public InterestPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(InterestView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getinterst(HomeRequest homeRequest) {
        getMvpView().showLoader();
        mDataManager.getinterest(homeRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<InterestResponseModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull InterestResponseModel loginResponse) {
                        getMvpView().showInterest(loginResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorInLogin();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void SaveInterest(SaveInterestRequest homeRequest) {
        getMvpView().showLoader();
        mDataManager.SaveInterest(homeRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<InterestResponseModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull InterestResponseModel loginResponse) {
                        getMvpView().showInterest(loginResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorInLogin();
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
