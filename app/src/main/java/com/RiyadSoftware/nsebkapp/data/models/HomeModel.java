package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HomeModel {
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


    public class Advertisement {

        @SerializedName("image")
        @Expose
        private String image;

        @SerializedName("link")
        @Expose
        private String link;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        @SerializedName("video")
        @Expose
        private String video;


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }

    public class Data {

        @SerializedName("now_Deals")
        @Expose
        private List<DealModel> now_Deals = null;
        @SerializedName("pervious_Deals")
        @Expose
        private List<DealModel> pervious_Deals = null;
        @SerializedName("coming_Deals")
        @Expose
        private List<DealModel> coming_Deals = null;
        @SerializedName("my_Deals")
        @Expose
        private List<DealModel> my_Deals = null;
        @SerializedName("advertisements")
        @Expose
        private List<Advertisement> advertisements = null;
        @SerializedName("count_now_Deals")
        @Expose
        private Integer count_now_Deals;
        @SerializedName("count_coming_Deals")
        @Expose
        private Integer count_coming_Deals;
        @SerializedName("count_pervious_Deals")
        @Expose
        private Integer count_pervious_Deals;

        public List<DealModel> getNow_Deals() {
            return now_Deals;
        }

        public void setNow_Deals(List<DealModel> now_Deals) {
            this.now_Deals = now_Deals;
        }

        public List<DealModel> getPervious_Deals() {
            return pervious_Deals;
        }

        public void setPervious_Deals(List<DealModel> pervious_Deals) {
            this.pervious_Deals = pervious_Deals;
        }

        public List<DealModel> getComing_Deals() {
            return coming_Deals;
        }

        public void setComing_Deals(List<DealModel> coming_Deals) {
            this.coming_Deals = coming_Deals;
        }

        public List<DealModel> getMy_Deals() {
            return my_Deals;
        }

        public void setMy_Deals(List<DealModel> my_Deals) {
            this.my_Deals = my_Deals;
        }

        public List<Advertisement> getAdvertisements() {
            return advertisements;
        }

        public void setAdvertisements(List<Advertisement> advertisements) {
            this.advertisements = advertisements;
        }

        public Integer getCount_now_Deals() {
            return count_now_Deals;
        }

        public void setCount_now_Deals(Integer count_now_Deals) {
            this.count_now_Deals = count_now_Deals;
        }

        public Integer getCount_coming_Deals() {
            return count_coming_Deals;
        }

        public void setCount_coming_Deals(Integer count_coming_Deals) {
            this.count_coming_Deals = count_coming_Deals;
        }

        public Integer getCount_pervious_Deals() {
            return count_pervious_Deals;
        }

        public void setCount_pervious_Deals(Integer count_pervious_Deals) {
            this.count_pervious_Deals = count_pervious_Deals;
        }

    }

    public class DealModel implements Serializable {

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

        public boolean isIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(boolean is_favorite) {
            this.is_favorite = is_favorite;
        }

        @SerializedName("is_favorite")
        @Expose
        private boolean is_favorite;
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

    }

//    public class Coming_Deal {
//
//        @SerializedName("Deal_id")
//        @Expose
//        private Integer deal_id;
//        @SerializedName("title")
//        @Expose
//        private String title;
//        @SerializedName("disc")
//        @Expose
//        private String disc;
//        @SerializedName("info")
//        @Expose
//        private String info;
//        @SerializedName("image")
//        @Expose
//        private String image;
//        @SerializedName("original_price")
//        @Expose
//        private String original_price;
//        @SerializedName("initial_price")
//        @Expose
//        private String initial_price;
//        @SerializedName("points")
//        @Expose
//        private String points;
//        @SerializedName("tickets")
//        @Expose
//        private String tickets;
//        @SerializedName("tender_cost")
//        @Expose
//        private String tender_cost;
//        @SerializedName("tender_edit_cost")
//        @Expose
//        private String tender_edit_cost;
//        @SerializedName("tender_coupon")
//        @Expose
//        private String tender_coupon;
//        @SerializedName("expiry_date")
//        @Expose
//        private String expiry_date;
//
//        public Integer getDeal_id() {
//            return deal_id;
//        }
//
//        public void setDeal_id(Integer deal_id) {
//            this.deal_id = deal_id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getDisc() {
//            return disc;
//        }
//
//        public void setDisc(String disc) {
//            this.disc = disc;
//        }
//
//        public String getInfo() {
//            return info;
//        }
//
//        public void setInfo(String info) {
//            this.info = info;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getOriginal_price() {
//            return original_price;
//        }
//
//        public void setOriginal_price(String original_price) {
//            this.original_price = original_price;
//        }
//
//        public String getInitial_price() {
//            return initial_price;
//        }
//
//        public void setInitial_price(String initial_price) {
//            this.initial_price = initial_price;
//        }
//
//        public String getPoints() {
//            return points;
//        }
//
//        public void setPoints(String points) {
//            this.points = points;
//        }
//
//        public String getTickets() {
//            return tickets;
//        }
//
//        public void setTickets(String tickets) {
//            this.tickets = tickets;
//        }
//
//        public String getTender_cost() {
//            return tender_cost;
//        }
//
//        public void setTender_cost(String tender_cost) {
//            this.tender_cost = tender_cost;
//        }
//
//        public String getTender_edit_cost() {
//            return tender_edit_cost;
//        }
//
//        public void setTender_edit_cost(String tender_edit_cost) {
//            this.tender_edit_cost = tender_edit_cost;
//        }
//
//        public String getTender_coupon() {
//            return tender_coupon;
//        }
//
//        public void setTender_coupon(String tender_coupon) {
//            this.tender_coupon = tender_coupon;
//        }
//
//        public String getExpiry_date() {
//            return expiry_date;
//        }
//
//        public void setExpiry_date(String expiry_date) {
//            this.expiry_date = expiry_date;
//        }
//
//    }
//
//    public class Pervious_Deal {
//
//        @SerializedName("Deal_id")
//        @Expose
//        private Integer deal_id;
//        @SerializedName("title")
//        @Expose
//        private String title;
//        @SerializedName("disc")
//        @Expose
//        private String disc;
//        @SerializedName("info")
//        @Expose
//        private String info;
//        @SerializedName("image")
//        @Expose
//        private String image;
//        @SerializedName("original_price")
//        @Expose
//        private String original_price;
//        @SerializedName("initial_price")
//        @Expose
//        private String initial_price;
//        @SerializedName("points")
//        @Expose
//        private String points;
//        @SerializedName("tickets")
//        @Expose
//        private String tickets;
//        @SerializedName("tender_cost")
//        @Expose
//        private String tender_cost;
//        @SerializedName("tender_edit_cost")
//        @Expose
//        private String tender_edit_cost;
//        @SerializedName("tender_coupon")
//        @Expose
//        private String tender_coupon;
//        @SerializedName("expiry_date")
//        @Expose
//        private String expiry_date;
//
//        public Integer getDeal_id() {
//            return deal_id;
//        }
//
//        public void setDeal_id(Integer deal_id) {
//            this.deal_id = deal_id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getDisc() {
//            return disc;
//        }
//
//        public void setDisc(String disc) {
//            this.disc = disc;
//        }
//
//        public String getInfo() {
//            return info;
//        }
//
//        public void setInfo(String info) {
//            this.info = info;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getOriginal_price() {
//            return original_price;
//        }
//
//        public void setOriginal_price(String original_price) {
//            this.original_price = original_price;
//        }
//
//        public String getInitial_price() {
//            return initial_price;
//        }
//
//        public void setInitial_price(String initial_price) {
//            this.initial_price = initial_price;
//        }
//
//        public String getPoints() {
//            return points;
//        }
//
//        public void setPoints(String points) {
//            this.points = points;
//        }
//
//        public String getTickets() {
//            return tickets;
//        }
//
//        public void setTickets(String tickets) {
//            this.tickets = tickets;
//        }
//
//        public String getTender_cost() {
//            return tender_cost;
//        }
//
//        public void setTender_cost(String tender_cost) {
//            this.tender_cost = tender_cost;
//        }
//
//        public String getTender_edit_cost() {
//            return tender_edit_cost;
//        }
//
//        public void setTender_edit_cost(String tender_edit_cost) {
//            this.tender_edit_cost = tender_edit_cost;
//        }
//
//        public String getTender_coupon() {
//            return tender_coupon;
//        }
//
//        public void setTender_coupon(String tender_coupon) {
//            this.tender_coupon = tender_coupon;
//        }
//
//        public String getExpiry_date() {
//            return expiry_date;
//        }
//
//        public void setExpiry_date(String expiry_date) {
//            this.expiry_date = expiry_date;
//        }
//
//    }

}
