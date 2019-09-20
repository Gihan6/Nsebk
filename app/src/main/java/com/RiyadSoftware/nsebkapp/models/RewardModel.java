package com.RiyadSoftware.nsebkapp.models;

import com.RiyadSoftware.nsebkapp.util.Constant;

public class RewardModel {

    private int id=0;
    private String points = "";
    private String start = "";
    private String end = "";
    private String image = "";



    public RewardModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getImage() {
        return Constant.baseImage+image;

    }

    public void setImage(String image) {
        this.image = image;
    }
}
