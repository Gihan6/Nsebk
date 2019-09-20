package com.RiyadSoftware.nsebkapp.Ui.Terms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TermsResponse {
    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("disc")
        @Expose
        private String disc;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

    }
    public class Contacts {

        @SerializedName("id")
        @Expose
        private Integer id;

        public String getEmail() {
            return email;
        }

        public String getPhone1() {
            return phone1;
        }

        public String getPhone2() {
            return phone2;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        @SerializedName("facebook")
        @Expose
        private String facebook;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone1")
        @Expose
        private String phone1;
        @SerializedName("phone2")
        @Expose
        private String phone2;
        @SerializedName("instagram")
        @Expose
        private String instagram;
        @SerializedName("twitter")
        @Expose
        private String twitter;
        @SerializedName("snapchat")
        @Expose
        private String snapchat;
        @SerializedName("whatsapp")
        @Expose
        private String whatsapp;
        @SerializedName("youtube")
        @Expose
        private String youtube;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getSnapchat() {
            return snapchat;
        }

        public void setSnapchat(String snapchat) {
            this.snapchat = snapchat;
        }

        public String getYoutube() {
            return youtube;
        }

        public void setYoutube(String youtube) {
            this.youtube = youtube;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
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
    private List<Datum> data = null;
    @SerializedName("contacts")
    @Expose
    private Contacts contacts;

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

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
