package com.RiyadSoftware.nsebkapp.data.models.coupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponResponse {

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



    public class Data {

        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name_en")
        @Expose
        private String name_en;

        public void setId(int id) {
            this.id = id;
        }

        public String getName_ar() {
            return name_ar;
        }

        public void setName_ar(String name_ar) {
            this.name_ar = name_ar;
        }

        @SerializedName("name_ar")
        @Expose
        private String name_ar;


        @SerializedName("code")
        @Expose
        private String code;



        @SerializedName("add_points")
        @Expose
        private int add_points;

        @SerializedName("add_tickets")
        @Expose
        private int add_tickets;

        @SerializedName("occurences")
        @Expose
        private int occurences;

        @SerializedName("expire_in")
        @Expose
        private String expire_in;


        @SerializedName("created_at")
        @Expose
        private String created_at;

        @SerializedName("updated_at")
        @Expose
        private String updated_at;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getAdd_points() {
            return add_points;
        }

        public void setAdd_points(Integer add_points) {
            this.add_points = add_points;
        }

        public Integer getAdd_tickets() {
            return add_tickets;
        }

        public void setAdd_tickets(Integer add_tickets) {
            this.add_tickets = add_tickets;
        }

        public Integer getOccurences() {
            return occurences;
        }

        public void setOccurences(Integer occurences) {
            this.occurences = occurences;
        }

        public String getExpire_in() {
            return expire_in;
        }

        public void setExpire_in(String expire_in) {
            this.expire_in = expire_in;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
