package com.RiyadSoftware.nsebkapp.data.models;

public class LoginRequest {
    String mobile , password;
    String device_type ,  device_token;
    public LoginRequest(String mobile, String password , String device_type , String device_token) {
        this.mobile = mobile;
        this.password = password;
        this.device_type = device_type;
        this.device_token = device_token;
    }
}
