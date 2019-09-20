package com.RiyadSoftware.nsebkapp.Ui.mydeal;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.HomeRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.MydealsResponse;
import com.RiyadSoftware.nsebkapp.injection.ConfigPersistent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@ConfigPersistent
public class Mydealspressenter extends BasePresenter<MyDealsView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public Mydealspressenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MyDealsView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getMydeals(HomeRequest loginRequest) {
        getMvpView().showLoader();
        mDataManager.getMydeals(loginRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MydealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull MydealsResponse loginResponse) {
                        getMvpView().showData(loginResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
