package com.RiyadSoftware.nsebkapp.Ui.forgetPassword;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.VerifyCodeResponse;

public interface ForgetView
        extends MvpView {

    public void navigateToNewPassword(VerifyCodeResponse response);

    public void showErrorInForgetPAssword();
}
