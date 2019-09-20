package com.RiyadSoftware.nsebkapp.data.models.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponRequest {

    @SerializedName("token")
    @Expose
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    @SerializedName("coupon_code")
    @Expose
    public String coupon_code;

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    @SerializedName("package_id")
    @Expose
    public int package_id;


}
