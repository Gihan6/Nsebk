package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTicketResponse {
    public class Data {

        @SerializedName("user_coupons")
        @Expose
        public String userCoupon;
        @SerializedName("user_points")
        @Expose
        public Integer userPoints;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("deal_id")
        @Expose
        public Integer dealId;

        public String getUserCoupon() {
            return userCoupon;
        }

        public void setUserCoupon(String userCoupon) {
            this.userCoupon = userCoupon;
        }

        public Integer getUserPoints() {
            return userPoints;
        }

        public void setUserPoints(Integer userPoints) {
            this.userPoints = userPoints;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getDealId() {
            return dealId;
        }

        public void setDealId(Integer dealId) {
            this.dealId = dealId;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

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
