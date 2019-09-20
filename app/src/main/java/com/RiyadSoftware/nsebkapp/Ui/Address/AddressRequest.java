package com.RiyadSoftware.nsebkapp.Ui.Address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressRequest {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("country_id")
    @Expose
    private String country_id;
    @SerializedName("city_id")
    @Expose
    private String city_id;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("zip_code")
    @Expose
    private String zip_code;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("disc")
    @Expose
    private String disc;
    @SerializedName("disc2")
    @Expose
    private String disc2;

    public String getDisc2() {
        return disc2;
    }

    public void setDisc2(String disc2) {
        this.disc2 = disc2;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }


}
