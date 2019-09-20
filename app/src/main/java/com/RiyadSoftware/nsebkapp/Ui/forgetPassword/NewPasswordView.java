package com.RiyadSoftware.nsebkapp.Ui.forgetPassword;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.forgetPassword.NewPasswordResponse;

public interface NewPasswordView
        extends MvpView {

    public void navigateToLogin(NewPasswordResponse response);

    public void showErrorInRequest();
}
