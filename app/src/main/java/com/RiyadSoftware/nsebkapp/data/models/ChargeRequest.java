package com.RiyadSoftware.nsebkapp.data.models;

public class ChargeRequest {


    String token ; int package_id;

    public ChargeRequest(String token, int package_id) {
        this.token = token;
        this.package_id = package_id;
    }
}
