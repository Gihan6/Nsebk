package com.RiyadSoftware.nsebkapp.Ui.forgetPassword;

import androidx.annotation.NonNull;

import com.RiyadSoftware.nsebkapp.base.BasePresenter;
import com.RiyadSoftware.nsebkapp.data.DataManager;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.NewPasswordRequest;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.NewPasswordResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewPasswordPrsenter extends BasePresenter<NewPasswordView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public NewPasswordPrsenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(NewPasswordView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void sendNewPassword(NewPasswordRequest newPasswordRequest) {
        getMvpView().showLoader();
        mDataManager.newPassword(newPasswordRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<NewPasswordResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull NewPasswordResponse response) {
                        getMvpView().navigateToLogin(response);
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showErrorInRequest(e.getMessage());
                        getMvpView().hideLoader();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
