package com.RiyadSoftware.nsebkapp.Ui.Address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addressresponse {

    public class Data {

        @SerializedName("user_id")
        @Expose
        private Integer user_id;
        @SerializedName("city_id")
        @Expose
        private String city_id;
        @SerializedName("country_id")
        @Expose
        private String country_id;
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
        @SerializedName("updated_at")
        @Expose
        private String updated_at;
        @SerializedName("created_at")
        @Expose
        private String created_at;
        @SerializedName("id")
        @Expose
        private Integer id;

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
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

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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
