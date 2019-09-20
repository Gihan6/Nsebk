package com.RiyadSoftware.nsebkapp.Ui.Contact;

import com.RiyadSoftware.nsebkapp.Ui.Terms.TermsResponse;
import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.ChargeHistoryResponse;

public interface ContactMvpView
        extends MvpView {

    void afterSendData(ContactResponse response);
    void showTerms(TermsResponse response);

}
