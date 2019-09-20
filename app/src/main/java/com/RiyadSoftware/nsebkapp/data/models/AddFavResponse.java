package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddFavResponse {


    public class Data {

        @SerializedName("Deal_id")
        @Expose
        private Integer deal_id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("disc")
        @Expose
        private String disc;
        @SerializedName("info")
        @Expose
        private String info;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("original_price")
        @Expose
        private String original_price;
        @SerializedName("initial_price")
        @Expose
        private String initial_price;
        @SerializedName("points")
        @Expose
        private String points;
        @SerializedName("tickets")
        @Expose
        private String tickets;
        @SerializedName("tender_cost")
        @Expose
        private String tender_cost;
        @SerializedName("tender_edit_cost")
        @Expose
        private String tender_edit_cost;
        @SerializedName("tender_coupon")
        @Expose
        private String tender_coupon;
        @SerializedName("expiry_date")
        @Expose
        private String expiry_date;
        @SerializedName("is_favorite")
        @Expose
        private String is_favorite;
        @SerializedName("deal_images")
        @Expose
        private List<Deal_image> deal_images = null;

        public Integer getDeal_id() {
            return deal_id;
        }

        public void setDeal_id(Integer deal_id) {
            this.deal_id = deal_id;
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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getInitial_price() {
            return initial_price;
        }

        public void setInitial_price(String initial_price) {
            this.initial_price = initial_price;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getTickets() {
            return tickets;
        }

        public void setTickets(String tickets) {
            this.tickets = tickets;
        }

        public String getTender_cost() {
            return tender_cost;
        }

        public void setTender_cost(String tender_cost) {
            this.tender_cost = tender_cost;
        }

        public String getTender_edit_cost() {
            return tender_edit_cost;
        }

        public void setTender_edit_cost(String tender_edit_cost) {
            this.tender_edit_cost = tender_edit_cost;
        }

        public String getTender_coupon() {
            return tender_coupon;
        }

        public void setTender_coupon(String tender_coupon) {
            this.tender_coupon = tender_coupon;
        }

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }

        public String getIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(String is_favorite) {
            this.is_favorite = is_favorite;
        }

        public List<Deal_image> getDeal_images() {
            return deal_images;
        }

        public void setDeal_images(List<Deal_image> deal_images) {
            this.deal_images = deal_images;
        }

    }
    public class Deal_image {

        @SerializedName("image")
        @Expose
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
