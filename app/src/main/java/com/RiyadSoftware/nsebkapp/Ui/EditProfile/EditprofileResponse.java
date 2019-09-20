package com.RiyadSoftware.nsebkapp.Ui.EditProfile;

import com.RiyadSoftware.nsebkapp.data.models.LoginResponse;
import com.RiyadSoftware.nsebkapp.data.models.RegisterResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditprofileResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("errors")
    @Expose
    private List<RegisterResponse.Error> errors;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private LoginResponse.Data user;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<RegisterResponse.Error> getErrors() {
        return errors;
    }

    public void setErrors(List<RegisterResponse.Error> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginResponse.Data getUser() {
        return user;
    }

    public void setUser(LoginResponse.Data user) {
        this.user = user;
    }

//    public class User {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("user_name")
//        @Expose
//        private String user_name;
//        @SerializedName("name")
//        @Expose
//        private String name;
//        @SerializedName("email")
//        @Expose
//        private String email;
//        @SerializedName("mobile")
//        @Expose
//        private String mobile;
//        @SerializedName("birth_date")
//        @Expose
//        private String birth_date;
//        @SerializedName("job")
//        @Expose
//        private String job;
//        @SerializedName("gender")
//        @Expose
//        private String gender;
//        @SerializedName("points")
//        @Expose
//        private String points;
//        @SerializedName("coupons")
//        @Expose
//        private String coupons;
//        @SerializedName("image")
//        @Expose
//        private String image;
//        @SerializedName("device_token")
//        @Expose
//        private Object device_token;
//        @SerializedName("role")
//        @Expose
//        private String role;
//        @SerializedName("status")
//        @Expose
//        private String status;
//        @SerializedName("available")
//        @Expose
//        private Object available;
//        @SerializedName("interested")
//        @Expose
//        private Object interested;
//        @SerializedName("type")
//        @Expose
//        private Object type;
//        @SerializedName("country_id")
//        @Expose
//        private Integer country_id;
//        @SerializedName("city_id")
//        @Expose
//        private Integer city_id;
//        @SerializedName("remember_token")
//        @Expose
//        private String remember_token;
//        @SerializedName("created_at")
//        @Expose
//        private String created_at;
//        @SerializedName("updated_at")
//        @Expose
//        private String updated_at;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getUser_name() {
//            return user_name;
//        }
//
//        public void setUser_name(String user_name) {
//            this.user_name = user_name;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public String getBirth_date() {
//            return birth_date;
//        }
//
//        public void setBirth_date(String birth_date) {
//            this.birth_date = birth_date;
//        }
//
//        public String getJob() {
//            return job;
//        }
//
//        public void setJob(String job) {
//            this.job = job;
//        }
//
//        public String getGender() {
//            return gender;
//        }
//
//        public void setGender(String gender) {
//            this.gender = gender;
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
//        public String getCoupons() {
//            return coupons;
//        }
//
//        public void setCoupons(String coupons) {
//            this.coupons = coupons;
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
//        public Object getDevice_token() {
//            return device_token;
//        }
//
//        public void setDevice_token(Object device_token) {
//            this.device_token = device_token;
//        }
//
//        public String getRole() {
//            return role;
//        }
//
//        public void setRole(String role) {
//            this.role = role;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public Object getAvailable() {
//            return available;
//        }
//
//        public void setAvailable(Object available) {
//            this.available = available;
//        }
//
//        public Object getInterested() {
//            return interested;
//        }
//
//        public void setInterested(Object interested) {
//            this.interested = interested;
//        }
//
//        public Object getType() {
//            return type;
//        }
//
//        public void setType(Object type) {
//            this.type = type;
//        }
//
//        public Integer getCountry_id() {
//            return country_id;
//        }
//
//        public void setCountry_id(Integer country_id) {
//            this.country_id = country_id;
//        }
//
//        public Integer getCity_id() {
//            return city_id;
//        }
//
//        public void setCity_id(Integer city_id) {
//            this.city_id = city_id;
//        }
//
//        public String getRemember_token() {
//            return remember_token;
//        }
//
//        public void setRemember_token(String remember_token) {
//            this.remember_token = remember_token;
//        }
//
//        public String getCreated_at() {
//            return created_at;
//        }
//
//        public void setCreated_at(String created_at) {
//            this.created_at = created_at;
//        }
//
//        public String getUpdated_at() {
//            return updated_at;
//        }
//
//        public void setUpdated_at(String updated_at) {
//            this.updated_at = updated_at;
//        }
//
//    }

}
