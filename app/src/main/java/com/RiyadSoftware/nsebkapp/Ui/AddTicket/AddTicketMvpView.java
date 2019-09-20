package com.RiyadSoftware.nsebkapp.Ui.AddTicket;

import com.RiyadSoftware.nsebkapp.base.MvpView;
import com.RiyadSoftware.nsebkapp.data.models.AddTicketResponse;
import com.RiyadSoftware.nsebkapp.data.models.DealDetailsResponse;

public interface AddTicketMvpView extends MvpView {

    void addedToTicket(AddTicketResponse addTicketResponse);
    void showDetails(DealDetailsResponse dealDetailsResponse);

}
