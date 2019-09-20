package com.RiyadSoftware.nsebkapp.Ui.mydeal;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.MydealsResponse;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;

public interface MyDealsView
        extends MvpView {

    public void showData(MydealsResponse response);

}
