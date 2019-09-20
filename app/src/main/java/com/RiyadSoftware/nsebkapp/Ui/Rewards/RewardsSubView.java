package com.RiyadSoftware.nsebkapp.Ui.Rewards;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.DealsResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.OffersResponseModel;
import com.RiyadSoftware.nsebkapp.data.models.RewardsResponse;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardResponse;

public interface RewardsSubView
        extends MvpView {

    public void getOffers(OffersResponseModel offersResponseModel);
    public void getRewards(RewardsResponse rewardsResponse);
    public void getReplaceAwardsResponse(ReplaceAwardResponse replaceAwardResponse);


}
