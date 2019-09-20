package com.RiyadSoftware.nsebkapp.Ui.Inteerst;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;

public interface InterestView
        extends MvpView {

    public void showInterest(InterestResponseModel response);
    public void showErrorInLogin();
}
