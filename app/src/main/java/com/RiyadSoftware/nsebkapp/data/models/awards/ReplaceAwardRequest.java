package com.RiyadSoftware.nsebkapp.data.models.awards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplaceAwardRequest {
    public ReplaceAwardRequest(String token, int award_id) {
        this.token = token;
        this.award_id = award_id;
    }

    @SerializedName("token")
    @Expose
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAwards_id() {
        return award_id;
    }

    public void setAward_id(int award_id) {
        this.award_id = award_id;
    }

    @SerializedName("award_id")
    @Expose
    public int award_id;

}
