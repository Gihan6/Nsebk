package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryProductResponse {
    public class Data {

        @SerializedName("Dealss")
        @Expose
        private List<HomeModel.DealModel> dealss = null;
        @SerializedName("count_Deals")
        @Expose
        private Integer count_Deals;

        public List<HomeModel.DealModel> getDealss() {
            return dealss;
        }

        public void setDealss(List<HomeModel.DealModel> dealss) {
            this.dealss = dealss;
        }

        public Integer getCount_Deals() {
            return count_Deals;
        }

        public void setCount_Deals(Integer count_Deals) {
            this.count_Deals = count_Deals;
        }

    }
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
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
