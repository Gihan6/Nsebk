package com.RiyadSoftware.nsebkapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryProductsRequest {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("subcategory_id")
    @Expose
    private Integer subcategory_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(Integer subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public CategoryProductsRequest(String token, Integer subcategory_id) {
        this.token = token;
        this.subcategory_id = subcategory_id;
    }
}
