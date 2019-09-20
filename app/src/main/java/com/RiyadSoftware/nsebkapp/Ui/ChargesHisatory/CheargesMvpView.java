package com.RiyadSoftware.nsebkapp.Ui.ChargesHisatory;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.ChargeHistoryResponse;

public interface CheargesMvpView
        extends MvpView {

    void showListOfCharges(ChargeHistoryResponse chargeResponse);

}
