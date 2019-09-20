package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("errors")
    @Expose
    private String errors;
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

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
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


    public class error {
        String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @SerializedName("image")
        @Expose
        private String image;

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        @SerializedName("verified")
        @Expose
        private boolean verified;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("user_name")
        @Expose
        private String user_name;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("country_id")
        @Expose
        private Integer country_id;
        @SerializedName("country_name")
        @Expose
        private String country_name;
        @SerializedName("city_id")
        @Expose
        private Integer city_id;
        @SerializedName("city_name")
        @Expose
        private String city_name;
        @SerializedName("job")
        @Expose
        private String job;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("points")
        @Expose
        private String points;
        @SerializedName("coupons")
        @Expose
        private String coupons;
        @SerializedName("remember_token")
        @Expose
        private String remember_token;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Integer getCountry_id() {
            return country_id;
        }

        public void setCountry_id(Integer country_id) {
            this.country_id = country_id;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public Integer getCity_id() {
            return city_id;
        }

        public void setCity_id(Integer city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getCoupons() {
            return coupons;
        }

        public void setCoupons(String coupons) {
            this.coupons = coupons;
        }

        public String getRemember_token() {
            return remember_token;
        }

        public void setRemember_token(String remember_token) {
            this.remember_token = remember_token;
        }

    }
}
