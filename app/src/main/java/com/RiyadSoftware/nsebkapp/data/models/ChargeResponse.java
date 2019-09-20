package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChargeResponse {

    public class Data {

        @SerializedName("user_name")
        @Expose
        private String user_name;
        @SerializedName("package")
        @Expose
        private String _package;
        @SerializedName("points")
        @Expose
        private String points;
        @SerializedName("package_id")
        @Expose
        private Integer package_id;
        @SerializedName("user_id")
        @Expose
        private Integer user_id;
        @SerializedName("updated_at")
        @Expose
        private String updated_at;
        @SerializedName("created_at")
        @Expose
        private String created_at;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPackage() {
            return _package;
        }

        public void setPackage(String _package) {
            this._package = _package;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public Integer getPackage_id() {
            return package_id;
        }

        public void setPackage_id(Integer package_id) {
            this.package_id = package_id;
        }

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
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
