package com.RiyadSoftware.nsebkapp.data.models;

public class AddFavRequest {
    String token , deal_id;

    public AddFavRequest(String token, String deal_id) {
        this.token = token;
        this.deal_id = deal_id;
    }
}

