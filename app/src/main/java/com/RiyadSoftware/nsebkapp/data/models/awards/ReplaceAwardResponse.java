package com.RiyadSoftware.nsebkapp.data.models.awards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplaceAwardResponse {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("errors")
    @Expose
    private String errors;

    @SerializedName("message")
    @Expose
    private String message;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
