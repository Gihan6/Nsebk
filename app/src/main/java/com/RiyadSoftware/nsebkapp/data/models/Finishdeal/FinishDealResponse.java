package com.RiyadSoftware.nsebkapp.data.models.Finishdeal;

import com.RiyadSoftware.nsebkapp.data.models.DealDetailsResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FinishDealResponse {

    public class Data {

        @SerializedName("DealDetails")
        @Expose
        private DealDetails dealDetails;
        @SerializedName("winners")
        @Expose
        private List<Winner> winners = null;

        public DealDetails getDealDetails() {
            return dealDetails;
        }

        public void setDealDetails(DealDetails dealDetails) {
            this.dealDetails = dealDetails;
        }

        public List<Winner> getWinners() {
            return winners;
        }

        public void setWinners(List<Winner> winners) {
            this.winners = winners;
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
        @SerializedName("images")
        @Expose
        private List<Object> images = null;
        @SerializedName("original_price")
        @Expose
        private Integer originalPrice;
        @SerializedName("initial_price")
        @Expose
        private Integer initialPrice;
        @SerializedName("points")
        @Expose
        private String points;
        @SerializedName("participants_no")
        @Expose
        private String participantsNo;
        @SerializedName("tickets")
        @Expose
        private String tickets;
        @SerializedName("tender_cost")
        @Expose
        private Integer tenderCost;
        @SerializedName("tender_edit_cost")
        @Expose
        private Integer tenderEditCost;
        @SerializedName("tender_coupon")
        @Expose
        private String tenderCoupon;
        @SerializedName("expiry_date")
        @Expose
        private String expiryDate;
        @SerializedName("is_favorite")
        @Expose
        private String isFavorite;
        @SerializedName("my_ticket_points")
        @Expose
        private Object myTicketPoints;

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

        public List<Object> getImages() {
            return images;
        }

        public void setImages(List<Object> images) {
            this.images = images;
        }

        public Integer getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(Integer originalPrice) {
            this.originalPrice = originalPrice;
        }

        public Integer getInitialPrice() {
            return initialPrice;
        }

        public void setInitialPrice(Integer initialPrice) {
            this.initialPrice = initialPrice;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getParticipantsNo() {
            return participantsNo;
        }

        public void setParticipantsNo(String participantsNo) {
            this.participantsNo = participantsNo;
        }

        public String getTickets() {
            return tickets;
        }

        public void setTickets(String tickets) {
            this.tickets = tickets;
        }

        public Integer getTenderCost() {
            return tenderCost;
        }

        public void setTenderCost(Integer tenderCost) {
            this.tenderCost = tenderCost;
        }

        public Integer getTenderEditCost() {
            return tenderEditCost;
        }

        public void setTenderEditCost(Integer tenderEditCost) {
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

        public Object getMyTicketPoints() {
            return myTicketPoints;
        }

        public void setMyTicketPoints(Object myTicketPoints) {
            this.myTicketPoints = myTicketPoints;
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



    public class Winner {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("points")
        @Expose
        private String points;

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

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }
}