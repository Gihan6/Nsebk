package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTicketRequest {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("deal_id")
    @Expose
    private String deal_id;
    @SerializedName("points")
    @Expose
    private String points;

    @SerializedName("tickets")
    @Expose
    private String tickets;

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(String deal_id) {
        this.deal_id = deal_id;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public AddTicketRequest(String token, String deal_id, String points,String tickets) {
        this.token = token;
        this.deal_id = deal_id;
        this.points = points;
        this.tickets=tickets;
    }
}
