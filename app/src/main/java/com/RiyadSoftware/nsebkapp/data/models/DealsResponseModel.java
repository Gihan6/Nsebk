package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DealsResponseModel {


    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Datum implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private String user_id;
        @SerializedName("category_id")
        @Expose
        private String category_id;
        @SerializedName("sub_id")
        @Expose
        private String sub_id;
        @SerializedName("country_id")
        @Expose
        private String country_id;
        @SerializedName("salary")
        @Expose
        private String salary;
        @SerializedName("min_salary")
        @Expose
        private String min_salary;
        @SerializedName("min_points")
        @Expose
        private String min_points;
        @SerializedName("buy_offer_time")
        @Expose
        private String buy_offer_time;
        @SerializedName("buy_offer_date")
        @Expose
        private String buy_offer_date;
        @SerializedName("created_at")
        @Expose
        private String created_at;
        @SerializedName("updated_at")
        @Expose
        private String updated_at;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("title_ar")
        @Expose
        private String title_ar;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("description_ar")
        @Expose
        private String description_ar;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("product_image")
        @Expose
        private String product_image;
        @SerializedName("image2")
        @Expose
        private Object image2;
        @SerializedName("image3")
        @Expose
        private Object image3;
        @SerializedName("image4")
        @Expose
        private Object image4;
        @SerializedName("image5")
        @Expose
        private Object image5;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getSub_id() {
            return sub_id;
        }

        public void setSub_id(String sub_id) {
            this.sub_id = sub_id;
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

        public String getMin_salary() {
            return min_salary;
        }

        public void setMin_salary(String min_salary) {
            this.min_salary = min_salary;
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

        public String getTitle_ar() {
            return title_ar;
        }

        public void setTitle_ar(String title_ar) {
            this.title_ar = title_ar;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription_ar() {
            return description_ar;
        }

        public void setDescription_ar(String description_ar) {
            this.description_ar = description_ar;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public Object getImage2() {
            return image2;
        }

        public void setImage2(Object image2) {
            this.image2 = image2;
        }

        public Object getImage3() {
            return image3;
        }

        public void setImage3(Object image3) {
            this.image3 = image3;
        }

        public Object getImage4() {
            return image4;
        }

        public void setImage4(Object image4) {
            this.image4 = image4;
        }

        public Object getImage5() {
            return image5;
        }

        public void setImage5(Object image5) {
            this.image5 = image5;
        }

    }

}
