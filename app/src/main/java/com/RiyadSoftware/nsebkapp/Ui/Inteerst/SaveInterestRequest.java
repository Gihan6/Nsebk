package com.RiyadSoftware.nsebkapp.Ui.Inteerst;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveInterestRequest {
    public SaveInterestRequest(String token, List<String> interests) {
        this.token = token;
        this.interests = interests;
    }

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("interests")
    @Expose
    private List<String> interests = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }


}
