package com.RiyadSoftware.nsebkapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountriesResponse {

    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<InnerDatum> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<InnerDatum> getData() {
        return data;
    }

    public void setData(List<InnerDatum> data) {
        this.data = data;
    }

    public class InnerDatum {

        @SerializedName("country_id")
        @Expose
        private Integer id;
        @SerializedName("country_image")
        @Expose
        private String country_image;
        @SerializedName("country_name")
        @Expose
        private String country_name;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCountry_image() {
            return country_image;
        }

        public void setCountry_image(String country_image) {
            this.country_image = country_image;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }
    }

}
