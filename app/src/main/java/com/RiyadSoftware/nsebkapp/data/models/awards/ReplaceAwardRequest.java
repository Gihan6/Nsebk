package com.RiyadSoftware.nsebkapp.data.models.awards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplaceAwardRequest {
    public ReplaceAwardRequest(String token, int awards_id) {
        this.token = token;
        this.awards_id = awards_id;
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
        return awards_id;
    }

    public void setAwards_id(int awards_id) {
        this.awards_id = awards_id;
    }

    @SerializedName("awards_id")
    @Expose
    public int awards_id;

}
