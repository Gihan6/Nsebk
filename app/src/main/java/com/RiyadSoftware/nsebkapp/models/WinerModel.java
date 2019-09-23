package com.RiyadSoftware.nsebkapp.models;

public class WinerModel {
    String winerNAme;
    String price;

    public WinerModel(String winerNAme, String price) {
        this.winerNAme = winerNAme;
        this.price = price;
    }

    public String getWinerNAme() {
        return winerNAme;
    }

    public void setWinerNAme(String winerNAme) {
        this.winerNAme = winerNAme;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
