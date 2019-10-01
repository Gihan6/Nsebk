package com.RiyadSoftware.nsebkapp.Ui.forgetPassword;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.VerifyResponse;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeResponse;

public interface ForgetView
        extends MvpView {

    public void navigateToNewPassword(VerifyResponse response);

    void  showErrorMessage(String e);
    public void showErrorInForgetPAssword();

    void  showLoading();
    void hideLoading();
}
