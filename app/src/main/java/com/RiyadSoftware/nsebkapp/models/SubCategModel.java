package com.RiyadSoftware.nsebkapp.models;

import com.RiyadSoftware.nsebkapp.util.Constant;

import java.io.Serializable;

public class SubCategModel implements Serializable {


    private String  id = "";
    private String cateId = "";
    private String nameAr  = "" ;
    private String nameEN = "";
    private String name =  "";
    private String image = "";
    private String create = "";
    private String update = "";



    public SubCategModel() {

    }


    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Constant.baseImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
