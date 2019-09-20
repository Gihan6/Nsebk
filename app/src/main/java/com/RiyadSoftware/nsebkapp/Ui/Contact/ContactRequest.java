package com.RiyadSoftware.nsebkapp.Ui.Contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactRequest {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("message")
    @Expose
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactRequest(String name, String mobile, String title, String email, String message) {
        this.name = name;
        this.mobile = mobile;
        this.title = title;
        this.email = email;
        this.message = message;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
