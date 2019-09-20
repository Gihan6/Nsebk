package com.RiyadSoftware.nsebkapp.models;

import com.RiyadSoftware.nsebkapp.util.Constant;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private String id = "";
    private String userId = "";
    private String image = "";
    private String subCateg = "";
    private String category_id = "";
    private String country_id = "";
    private String salary = "";
    private String minSalary = "";
    private String min_points = "";
    private String buy_offer_time = "";
    private String buy_offer_date = "";
    private String created_at = "";
    private String updated_at = "";
    private String title = "";
    private String description = "";

    private String titleAr ="";
    private String titleEn = "";
    private String desAr = "";
    private String desEn = "";

    private String status =  "";


    public ProductModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMin_points() {
        return min_points;
    }

    public void setMin_points(String min_points) {
        this.min_points = min_points;
    }

    public String getBuy_offer_time() {
        return buy_offer_time;
    }

    public void setBuy_offer_time(String buy_offer_time) {
        this.buy_offer_time = buy_offer_time;
    }

    public String getBuy_offer_date() {
        return buy_offer_date;
    }

    public void setBuy_offer_date(String buy_offer_date) {
        this.buy_offer_date = buy_offer_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImage() {
        return Constant.baseImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getSubCateg() {
        return subCateg;
    }

    public void setSubCateg(String subCateg) {
        this.subCateg = subCateg;
    }


    public String getTitleAr() {
        return titleAr;
    }

    public void setTitleAr(String titleAr) {
        this.titleAr = titleAr;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDesAr() {
        return desAr;
    }

    public void setDesAr(String desAr) {
        this.desAr = desAr;
    }

    public String getDesEn() {
        return desEn;
    }

    public void setDesEn(String desEn) {
        this.desEn = desEn;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
