package com.RiyadSoftware.nsebkapp.Ui.forgetPassword;

import androidx.annotation.NonNull;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.LoginRequest;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeRequest;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForgetPresenter extends BasePresenter<ForgetView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public ForgetPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ForgetView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }



    public void verifyCodeForgetPassword(Map<String , String> body) {
        getMvpView().showLoader();
        mDataManager.verifyUser(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VerifyResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull VerifyResponse countriesResponse) {
                        getMvpView().navigateToNewPassword(countriesResponse);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorMessage(e.getMessage());
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                        getMvpView().hideLoader();
                    }
                });
    }


}
