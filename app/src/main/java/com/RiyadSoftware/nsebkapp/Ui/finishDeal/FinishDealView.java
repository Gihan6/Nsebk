package com.RiyadSoftware.nsebkapp.Ui.finishDeal;

import com.RiyadSoftware.nsebkapp.activities.FinishDeal;
import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealRequest;
import com.RiyadSoftware.nsebkapp.data.models.Finishdeal.FinishDealResponse;
import com.RiyadSoftware.nsebkapp.data.models.awards.ReplaceAwardResponse;

public interface FinishDealView extends MvpView {

    void getFinishDealData(FinishDealResponse response);

}
