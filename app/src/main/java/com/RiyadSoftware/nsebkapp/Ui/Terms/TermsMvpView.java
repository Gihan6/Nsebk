package com.RiyadSoftware.nsebkapp.Ui.Terms;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;

public interface TermsMvpView
        extends MvpView {

    public void showTerms(TermsResponse offersResponseModel);

}
