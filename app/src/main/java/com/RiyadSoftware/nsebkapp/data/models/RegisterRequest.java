package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    public RegisterRequest(String name , String user_name, String email, String password, String country, String gender, String city, String job, String birth_date, String mobile) {
        this.user_name = user_name;
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.gender = gender;
        this.city = city;
        this.job = job;
        this.birth_date = birth_date;
        this.mobile = mobile;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("birth_date")
    @Expose
    private String birth_date;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("image")
    @Expose
    private String image;

    public String getName() {
        return user_name;
    }

    public void setName(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry_id() {
        return country;
    }

    public void setCountry_id(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getmobile() {
        return mobile;
    }

    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
