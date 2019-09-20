package com.RiyadSoftware.nsebkapp.Ui.Login;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;

public interface LoginView
        extends MvpView {

    public void navigateToMain(LoginResponse response);
    public void showErrorInLogin();
}
