package com.RiyadSoftware.nsebkapp.Ui.Reset;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;

public interface ResetSubView
        extends MvpView {

    public void showMessage(ForgetResponse offersResponseModel);
}
