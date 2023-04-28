package com.digitalnomads.android.models;

import java.util.List;

public class PlaceModel {
    private String name;
    private String address;
    private String openingsHours;
    private String extra;
    private List<Integer> idsImage;
    private int idMainImage;
    private float stars;

    public PlaceModel(String name, String address, String openingsHours, String extra, List<Integer> idsImage, int idMainImage, float stars) {
        this.name = name;
        this.address = address;
        this.openingsHours = openingsHours;
        this.extra = extra;
        this.idsImage = idsImage;
        this.idMainImage = idMainImage;
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningsHours() {
        return openingsHours;
    }

    public void setOpeningsHours(String openingsHours) {
        this.openingsHours = openingsHours;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public List<Integer> getIdsImage() {
        return idsImage;
    }

    public void setIdsImage(List<Integer> idsImage) {
        this.idsImage = idsImage;
    }

    public int getIdMainImage() {
        return idMainImage;
    }

    public void setIdMainImage(int idMainImage) {
        this.idMainImage = idMainImage;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
