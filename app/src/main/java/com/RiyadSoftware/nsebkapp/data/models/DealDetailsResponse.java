package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DealDetailsResponse {
    public class Data {

        @SerializedName("DealDetails")
        @Expose
        private DealDetails dealDetails;

        public DealDetails getDealDetails() {
            return dealDetails;
        }

        public void setDealDetails(DealDetails dealDetails) {
            this.dealDetails = dealDetails;
        }

    }
    public class DealDetails {

        @SerializedName("Deal_id")
        @Expose
        private Integer dealId;
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
        private String originalPrice;
        @SerializedName("initial_price")
        @Expose
        private String initialPrice;
        @SerializedName("points")
        @Expose
        private String points;
        @SerializedName("tickets")
        @Expose
        private String tickets;
        @SerializedName("tender_cost")
        @Expose
        private String tenderCost;
        @SerializedName("tender_edit_cost")
        @Expose
        private String tenderEditCost;
        @SerializedName("tender_coupon")
        @Expose
        private String tenderCoupon;
        @SerializedName("winner_name")
        @Expose
        private String winner_name  ;

        public String getWinner_name() {
            return winner_name;
        }

        public void setWinner_name(String winner_name) {
            this.winner_name = winner_name;
        }

        public String getWinner_id() {
            return winner_id;
        }

        public void setWinner_id(String winner_id) {
            this.winner_id = winner_id;
        }

        @SerializedName("winner_id")
        @Expose
        private String winner_id  ;

        public List<String> getFive_second_users() {
            return five_second_users;
        }

        @SerializedName("five_second_users")
        @Expose
        private List<String> five_second_users  ;
        @SerializedName("expiry_date")
        @Expose
        private String expiryDate;



        @SerializedName("countries")
        @Expose
        private List<Countries> countries  ;

        @SerializedName("cities")
        @Expose
        private List<Cities> cities  ;

        public List<Cities> getCities() {
            return cities;
        }

        public void setCities(List<Cities> cities) {
            this.cities = cities;
        }

        public List<Countries> getCountries() {
            return countries;
        }

        public void setCountries(List<Countries> countries) {
            this.countries = countries;
        }

        public String getMy_ticket_points() {
            return my_ticket_points;
        }

        public void setMy_ticket_points(String my_ticket_points) {
            this.my_ticket_points = my_ticket_points;
        }

        @SerializedName("my_ticket_points")
        @Expose
        private String my_ticket_points;
        @SerializedName("is_favorite")
        @Expose
        private String isFavorite;
        @SerializedName("images")
        @Expose
        private List<Object> images = null;

        public Integer getDealId() {
            return dealId;
        }

        public void setDealId(Integer dealId) {
            this.dealId = dealId;
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

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getInitialPrice() {
            return initialPrice;
        }

        public void setInitialPrice(String initialPrice) {
            this.initialPrice = initialPrice;
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

        public String getTenderCost() {
            return tenderCost;
        }

        public void setTenderCost(String tenderCost) {
            this.tenderCost = tenderCost;
        }

        public String getTenderEditCost() {
            return tenderEditCost;
        }

        public void setTenderEditCost(String tenderEditCost) {
            this.tenderEditCost = tenderEditCost;
        }

        public String getTenderCoupon() {
            return tenderCoupon;
        }

        public void setTenderCoupon(String tenderCoupon) {
            this.tenderCoupon = tenderCoupon;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }

        public String getIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(String isFavorite) {
            this.isFavorite = isFavorite;
        }

        public void setFive_second_users(List<String> five_second_users) {
            this.five_second_users = five_second_users;
        }

        public List<Object> getImages() {
            return images;
        }

        public void setImages(List<Object> images) {
            this.images = images;
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

class Countries{
    @SerializedName("name_en")
    @Expose
    private String name_en;

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
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
    }

    class Cities{
        @SerializedName("name_en")
        @Expose
        private String name_en;

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
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
    }

}
