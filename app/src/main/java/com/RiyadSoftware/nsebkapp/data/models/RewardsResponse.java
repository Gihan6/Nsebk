package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RewardsResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("errors")
    @Expose
    private Object errors;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }



    public class Datum {

        @SerializedName("id")
        @Expose
        private int award_id;

        public int getAward_id() {
            return award_id;
        }

        public void setAward_id(int award_id) {
            this.award_id = award_id;
        }

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("coupons")
        @Expose
        private String coupons;
        @SerializedName("images")
        @Expose
        private String images;


        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @SerializedName("quantity")
        @Expose
        private int quantity;

        @SerializedName("expiry_date")
        @Expose
        private String expiry_date;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoupons() {
            return coupons;
        }

        public void setCoupons(String coupons) {
            this.coupons = coupons;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }

    }
}
