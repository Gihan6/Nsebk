package com.RiyadSoftware.nsebkapp.data.models.Finishdeal;

public class FinishDealRequest {

    String token;
    int Deal_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getDeal_id() {
        return Deal_id;
    }

    public void setDeal_id(int deal_id) {
        Deal_id = deal_id;
    }

    public FinishDealRequest(String token, int deal_id) {
        this.token = token;
        Deal_id = deal_id;
    }
}
