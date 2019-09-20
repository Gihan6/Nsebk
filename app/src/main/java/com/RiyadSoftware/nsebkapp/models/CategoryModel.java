package com.RiyadSoftware.nsebkapp.models;

import com.RiyadSoftware.nsebkapp.util.Constant;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private int id = 0 ;
    private String nameAr = "" ;
    private String nameEN = "" ;
    private String image = "";
    private String create = "";
    private String update = "";
    private String name = "";



    public CategoryModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return Constant.baseImage+ "/"+image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
