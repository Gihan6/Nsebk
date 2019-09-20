package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTicketResponse {
    public class Data {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("deal_id")
        @Expose
        public Integer dealId;
        @SerializedName("points")
        @Expose
        public String points;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("id")
        @Expose
        public Integer id;

    }

    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("errors")
    @Expose
    public Object errors;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

}
